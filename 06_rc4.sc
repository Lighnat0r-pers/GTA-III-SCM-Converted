// *******************************************************************************************
// *******************************************************************************************
// **************************************RC Destruction Derby*********************************
// ****************************************Casino Calamity************************************
// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************

:M06_CASINOCALAMITY
03A4: name_thread 'RC4'
gosub @MISSION_START_RC4
if 
	0112:   has_deatharrest_been_executed 
then
	gosub @MISSION_RC4_FAILED
end

:MISSION_END_RC4
gosub @MISSION_CLEANUP_RC4
end_thread

:MISSION_START_RC4
0004: $ONMISSION = 1
if 
	0038:   $CASINO_CALAMITY_COMPLETED == 0 
then
	0317: increment_mission_attempts
end
00BA: print_big 'RC3' duration 15000 ms style 2  // 'CASINO CALAMITY'
wait 0 ms 
0004: $COUNTER_RC = 0 
0004: $FLAG_BUGGY_HELP1_HM2 = 0 
0004: $CONTROLMODE = 0 
0004: $REWARD_RC = 0 
0005: $CAM_X = 370.0 
0005: $CAM_Y = -1316.0 
0005: $CAM_Z = 29.0 
0005: $RC_X = 373.0 
0005: $RC_Y = -1317.0 
0005: $RC_Z = 26.5 
0004: $TIMER_RC = 120000 
0297: clear_rampage_kills 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
01B4: set_player $PLAYER_CHAR controllable 0 
01C0: $WANTED_4X4 = player $PLAYER_CHAR wanted_level 
0110: clear_player $PLAYER_CHAR wanted_level 
00DA: $RC_VAN = store_car_player_is_in $PLAYER_CHAR
02A3: toggle_widescreen 1 

//UP GANGCAR NUMBERS AND DENSITY
0152: set_zone_car_info 'YAKUSA' DAY 20 0 0 0 270 0 0 0 10 300 200 200 0 0 0 
0152: set_zone_car_info 'YAKUSA' NIGHT 15 0 0 0 290 0 0 0 10 300 200 200 0 0 0 

015F: set_camera_position $CAM_X $CAM_Y $CAM_Z 0.0 rotation 0.0 0.0 
if
	8119:   not car $RC_VAN wrecked 
then
	020A: set_car $RC_VAN door_status_to CARLOCK_LOCKED
	0158: camera_on_vehicle $RC_VAN mode FIXED switchstyle JUMP_CUT
	0395: clear_area 1 at $RC_X $RC_Y range $RC_Z 5.0 
end
00BC: print_now 'RC_3' duration 4000 ms flag 1  // You have 2 minutes to blow up as many Yakuza Gang Cars as possible!
0247: request_model #RCBANDIT 
while 8248:   not model #RCBANDIT available 
	wait 0 ms
end

03C4: set_status_text_to $COUNTER_RC COUNTER_DISPLAY_NUMBER 'KILLS'  // KILLS:
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
		00BC: print_now 'WRECKED' duration 3000 ms flag 1  // ~r~The vehicle is wrecked!
		goto @MISSION_RC4_FAILED
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
			goto @MISSION_RC4_FAILED
		end
	else
		goto @MISSION_RC4_FAILED
	end
	0298: $COUNTER_RC = rampage_kills #YAKUZA 
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
	001C:   $COUNTER_RC > $RC4_RECORD
then
	0084: $REWARD_RC = $COUNTER_RC 
	0060: $REWARD_RC -= $RC4_RECORD 
	0010: $REWARD_RC *= 1000 
	0084: $RC4_RECORD = $COUNTER_RC
	goto @MISSION_RC4_PASSED
end

// Mission rc4 failed
:MISSION_RC4_FAILED
00BA: print_big 'M_FAIL' duration 5000 ms style 1  // MISSION FAILED!
00BC: print_now 'NRECORD' duration 5000 ms flag 1  // ~r~NO NEW RECORD!
goto @MISSION_END_RC4 

// Mission rc4 passed
:MISSION_RC4_PASSED
01E3: text_1number_styled 'M_PASS' number $REWARD_RC duration 5000 ms style 1  // MISSION PASSED! $~1~
00BC: print_now 'RECORD' duration 3000 ms flag 1  // ~g~NEW RECORD!!
0394: play_mission_passed_music 1 
0109: player $PLAYER_CHAR money += $REWARD_RC
if
	0038:   $CASINO_CALAMITY_COMPLETED == 0 
then
	030C: set_mission_points += 1 
	0004: $CASINO_CALAMITY_COMPLETED = 1 
	0318: set_latest_mission_passed 'RC4'  // 'CASINO CALAMITY'
end
042F: save_record 3 $RC4_RECORD 
goto @MISSION_END_RC4 

// mission cleanup
:MISSION_CLEANUP_RC4
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

0152: set_zone_car_info 'YAKUSA' DAY 20 0 0 0 100 0 0 0 20 350 200 250 0 0 0 
0152: set_zone_car_info 'YAKUSA' NIGHT 15 0 0 0 150 0 0 0 10 350 200 200 0 0 0 

00D8: mission_has_finished 
return 
