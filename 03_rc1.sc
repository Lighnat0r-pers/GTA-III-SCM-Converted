// *******************************************************************************************
// *******************************************************************************************
// **************************************RC Destruction Derby*********************************
// ***************************************Diablo Demolition***********************************
// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************

:M03_DIABLODESTRUCTION
03A4: name_thread 'RC1'
gosub @MISSION_START_RC1
if 
	0112:   has_deatharrest_been_executed 
then
	gosub @MISSION_RC1_FAILED
end
gosub @MISSION_CLEANUP_RC1
end_thread

// ***************************************Mission Start*************************************

:MISSION_START_RC1
0004: $ONMISSION = 1
if 
	0038:   $DIABLO_DESTRUCTION_COMPLETED == 0 
then
	0317: increment_mission_attempts
end
00BA: print_big 'RC1' time 15000 style 2  // 'DIABLO DESTRUCTION'
0001: wait 0 ms 
0004: $COUNTER_RC = 0 
0004: $FLAG_BUGGY_HELP1_HM2 = 0 
0004: $CONTROLMODE = 0 
0004: $REWARD_RC = 0 
0005: $CAM_X = 1019.0 
0005: $CAM_Y = -113.5 
0005: $CAM_Z = 9.0 
0005: $RC_X = 1026.0 
0005: $RC_Y = -117.0 
0005: $RC_Z = 5.5 
0004: $TIMER_RC = 120000 
0297: clear_rampage_kills 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
01B4: set_player $PLAYER_CHAR controllable 0 
01C0: $WANTED_4X4 = player $PLAYER_CHAR wanted_level 
0110: clear_player $PLAYER_CHAR wanted_level 
00DA: store_car_player_is_in $PLAYER_CHAR store_to $RC_VAN 
02A3: toggle_widescreen 1 

//UP GANGCAR NUMBERS AND DENSITY
0152: set_zone_car_info 'TOWERS' 1 8 0 0 200 0 0 0 0 20 400 0 0 350 0 0 
0152: set_zone_car_info 'TOWERS' 0 6 0 0 250 0 0 0 0 10 540 0 0 200 0 0 

015F: set_camera_position $CAM_X $CAM_Y $CAM_Z 0.0 rotation 0.0 0.0 
if
	8119:   not car $RC_VAN wrecked 
then
	020A: set_car $RC_VAN door_status_to CARLOCK_LOCKED
	0158: camera_on_vehicle $RC_VAN FIXED switchstyle JUMP_CUT
	0395: clear_area 1 at $RC_X $RC_Y range $RC_Z 5.0 
end
00BC: print_now 'RC_1' time 4000 flag 1  // You have 2 minutes to blow up as many Diablo Gang Cars as possible!
0247: request_model #RCBANDIT 
0247: request_model #DIABLOS 
while true
	if or
		8248:   not model #RCBANDIT available 
		8248:   not model #DIABLOS available 
	jf break
	wait 0 ms
end

// 010C: change_player_into_rc_buggy $PLAYER_CHAR at $RC_X $RC_Y $RC_Z 180.0 // Removed by R*
03C4: set_status_text_to $COUNTER_RC 0 'KILLS'  // KILLS:
014E: start_timer_at $TIMER_RC 
01BD: $TIMER_INTRO_START = current_time_in_ms
while 801A:   NOT   1 > $COUNTDOWN_TIME1 
	wait 0 ms
	01BD: $TIMER_INTRO_NOW = current_time_in_ms 
	0084: $INTRO_TIME_LAPSED = $TIMER_INTRO_NOW 
	0060: $INTRO_TIME_LAPSED -= $TIMER_INTRO_START 
	if
		0119:   car $RC_VAN wrecked 
	then
		00BC: print_now 'WRECKED' time 3000 flag 1  // ~r~The vehicle is wrecked!
		jump @MISSION_RC1_FAILED
	end
	if
		0256:   is_player $PLAYER_CHAR defined
	then
		0110: clear_player $PLAYER_CHAR wanted_level 
		if and
			0018:   $INTRO_TIME_LAPSED > 4000 
			0038:   $FLAG_BUGGY_HELP1_HM2 == 0 
		then
			0293: $CONTROLMODE = get_controller_mode
			if
				0038:   $CONTROLMODE == 3 
			then
				03E5: text_box 'RCHELPA'  // Press the ~k~~PED_FIREWEAPON~ button, or drive the RC car into a vehicle's wheels to detonate.
			else
				03E5: text_box 'RCHELP'  // Press ~k~~PED_FIREWEAPON~, or drive the RC car into a vehicle's wheels to detonate.
			end
			0004: $FLAG_BUGGY_HELP1_HM2 = 1 
			02A3: toggle_widescreen 0 
			01B4: set_player $PLAYER_CHAR controllable 1 
			015A: restore_camera 
		end
		if
			8442:   not player $PLAYER_CHAR in_car $RC_VAN
		then
			jump @MISSION_RC1_FAILED
		end
	else
		jump @MISSION_RC1_FAILED
	end
	0298: $COUNTER_RC = rampage_kills #DIABLOS 
	if
		0018:   $INTRO_TIME_LAPSED > 4000 
	then
		if
			8241:   not player $PLAYER_CHAR in_remote_mode 
		then
			010C: change_player_into_rc_buggy $PLAYER_CHAR at $RC_X $RC_Y $RC_Z 180.0 
		end
	end
end
014F: stop_timer $TIMER_RC 
0151: remove_status_text $COUNTER_RC 
0409: blow_up_rc_buggy 
0006: 16@ = 0
while 001B:   1500 > 16@ 
	wait 0 ms
end

if
	001C:   $COUNTER_RC > $RC1_RECORD
then
	0084: $REWARD_RC = $COUNTER_RC 
	0060: $REWARD_RC -= $RC1_RECORD 
	0010: $REWARD_RC *= 1000 
	0084: $RC1_RECORD = $COUNTER_RC
	goto @MISSION_RC1_PASSED
end

// Mission rc1 failed
:MISSION_RC1_FAILED
00BA: print_big 'M_FAIL' time 5000 style 1  // MISSION FAILED!
00BC: print_now 'NRECORD' time 5000 flag 1  // ~r~NO NEW RECORD!
return 

// Mission rc1 passed
:MISSION_RC1_PASSED
01E3: text_1number_styled 'M_PASS' number $REWARD_RC time 5000 style 1  // MISSION PASSED! $~1~
00BC: print_now 'RECORD' time 3000 flag 1  // ~g~NEW RECORD!!
0394: play_music 1 
0109: player $PLAYER_CHAR money += $REWARD_RC
if
	0038:   $DIABLO_DESTRUCTION_COMPLETED == 0 
then
	030C: progress_made = 1 
	0004: $DIABLO_DESTRUCTION_COMPLETED = 1 
	0318: set_latest_mission_passed 'RC1'  // 'DIABLO DESTRUCTION
end
042F: save_record 1 $RC1_RECORD 
return 

// mission cleanup
:MISSION_CLEANUP_RC1
0004: $ONMISSION = 0 
0004: $JUST_DONE_RC_MISSION = 1 
03CB: load_scene $CAM_X $CAM_Y $CAM_Z 
0249: release_model #RCBANDIT 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
01B4: set_player $PLAYER_CHAR controllable 1 
015A: restore_camera 
02A3: toggle_widescreen 0 
010D: set_player $PLAYER_CHAR wanted_level_to $WANTED_4X4 
014F: stop_timer $TIMER_RC 
0151: remove_status_text $COUNTER_RC 
0409: blow_up_rc_buggy 
if 
	8119:   not car $RC_VAN wrecked 
then 
	020A: set_car $RC_VAN door_status_to CARLOCK_UNLOCKED
end

0152: set_zone_car_info 'TOWERS' DAY 8 0 0 100 0 0 0 0 20 400 0 0 350 0 0 
0152: set_zone_car_info 'TOWERS' NIGHT 6 0 0 150 0 0 0 0 10 550 0 0 200 0 0 

0249: release_model #DIABLOS 
00D8: mission_has_finished 
0051: return 
