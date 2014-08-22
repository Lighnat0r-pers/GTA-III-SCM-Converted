// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************
// ***********************************Meat Factory Mission 4**********************************
// **************************************"The Loan Shark"*************************************
// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************

:M18_HERLOVER

03A4: name_thread 'MEAT4' 

// Mission Start Stuff
gosub @MISSION_START_MEAT4
if
	0112:   has_deatharrest_been_executed
then
	gosub @MISSION_FAILED_MEAT4
end

:MISSION_END_MEAT4
gosub @MISSION_CLEANUP_MEAT4
end_thread

// ****************************************Mission Start************************************

:MISSION_START_MEAT4

0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_MARTY = 1 
0317: increment_mission_attempts 
0001: wait 0 ms 
0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT4 = 0 
0004: $FLAG_LOANSHARK_IN_AREA = 0 
0004: $FLAG_OWNER_DEAD_MEAT4 = 0 
0004: $BLOB_FLAG = 1 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 0

// ********************************START OF CUTSCENE****************************************

03DE: set_ped_density_multiplier 0.0 
042B: clear_peds_from_cube 1164.25 -888.8125 10.0 1291.75 -811.6875 20.0 
02E4: load_cutscene_data 'MT_PH4' 
0244: set_cutscene_pos 1223.875 -839.375 13.9375 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
016A: fade 1 for 1500 ms 
03AF: set_streaming 1 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime 

// Displays cutscene text

while 001A:   2096 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_A' duration 10000 ms flag 1  // Damn, I'm in trouble!
while 001A:   3885 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_B' duration 10000 ms flag 1  // Turns out my wife was seeing some guy I owe money to.
while 001A:   7252 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_C' duration 10000 ms flag 1  // He's got real angry and he's looking for payback!
while 001A:   10502 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_D' duration 10000 ms flag 1  // I've agreed to see him...
while 001A:   11844 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_E' duration 10000 ms flag 1  // he thinks I'm gonna pay him off...
while 001A:   13374 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_F' duration 10000 ms flag 1  // but my guess is...
while 001A:   14622 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA4_G' duration 10000 ms flag 1  // Liberty's dogs are gonna get yet another flavor this month!
while 001A:   17623 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
03D5: remove_text 'MEA4_G'  // Liberty's dogs are gonna get yet another flavor this month!
while 001A:   18233 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end

016A: fade 0 for 1500 ms 

while 82E9:   not cutscene_reached_end
	wait 0 ms
end

00BE: clear_prints 

while fading
	wait 0 ms
end

02EA: end_cutscene 
0001: wait 500 ms 
016A: fade 1 for 1500 ms 
03DE: set_ped_density_multiplier 1.0 

// *****************************************END OF CUTSCENE*********************************

0247: request_model #FAN_MAN2 
0247: request_model #B_MAN2 
0247: request_model #STALLION 
03CF: load_wav 'MF3_A'

while true
	if or
		8248:   not model #FAN_MAN2 available 
		8248:   not model #B_MAN2 available 
		8248:   not model #STALLION available 
		83D0:   not wav_loaded
	jf break
	wait 0 ms
end //while

00A5: $CAR_MEAT4 = create_car #STALLION at 1190.0 -796.0 13.75
0175: set_car $CAR_MEAT4 z_angle_to 300.0 
0186: $RADAR_BLIP_CAR_MEAT4 = create_marker_above_car $CAR_MEAT4

// waiting for the player to get into the car

while 80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT4
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT4
end //while

0164: disable_marker $RADAR_BLIP_CAR_MEAT4 
00BC: print_now 'MEA4_B3' duration 7000 ms flag 1  // ~g~Pick up his wife's lover
009A: $LOANSHARK_MEAT4 = create_char PEDTYPE_CIVMALE model #FAN_MAN2 at 897.0 -476.0 14.5625
0243: set_actor $LOANSHARK_MEAT4 ped_stats_to PEDSTAT_TOUGH_GUY
01ED: clear_actor $LOANSHARK_MEAT4 threat_search 
01BE: set_actor $LOANSHARK_MEAT4 to_look_at_spot 895.0 -486.0 -100.0 
0187: $RADAR_BLIP_LOANSHARK_MEAT4 = create_marker_above_actor $LOANSHARK_MEAT4
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1 
039E: set_char_cant_be_dragged_out $LOANSHARK_MEAT4 to 1 

// Waiting for the player to be in the area

while true
	if or
		80EB:   not player $PLAYER_CHAR 0 $LOANSHARK_MEAT4 radius 8.0 8.0 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT4 
	jf break
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT4
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT4
end //while

// tells the lover to get into players car

01D4: actor $LOANSHARK_MEAT4 go_to_car $CAR_MEAT4 and_enter_it_as_a_passenger

while true
	if or
		80DB:   not is_char_in_car $LOANSHARK_MEAT4 car $CAR_MEAT4 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT4
	jf break
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT4
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT4
	gosub @CHECK_LOVER_RANGE_STATUS
end //while

0164: disable_marker $RADAR_BLIP_LOANSHARK_MEAT4 
03D1: play_wav 
00BC: print_now 'MEA4_B4' duration 10000 ms flag 1  // Marty sent you huh? OK, I'm gonna show that creep the meaning of the word business.
if
	03D2:   wav_ended
then
	03D5: remove_text 'MEA3_B4'  // Marty wants to see me? Well it better be quick because I have to get my hair done.
end

018A: $RADAR_BLIP_COORD2_MEAT4 = create_checkpoint_at 1217.0 -794.0 -100.0
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 2
009A: $OWNER_MEAT4 = create_char PEDTYPE_CIVMALE model #B_MAN2 at 1208.0 -789.0 13.875
01ED: clear_actor $OWNER_MEAT4 threat_search 
01BE: set_actor $OWNER_MEAT4 to_look_at_spot 1210.0 -791.0 -100.0 
0004: $BLOB_FLAG = 1

// waiting for the lover to be at the factory

while true
	if or
		8103:   not actor $LOANSHARK_MEAT4 stopped_near_point_in_car 1217.0 -794.0 13.875 radius 4.0 4.0 6.0 sphere $BLOB_FLAG 
		80DB:   not is_char_in_car $LOANSHARK_MEAT4 car $CAR_MEAT4 
	jf break
	wait 0 ms
	if
		03D2:   wav_ended
	then
		03D5: remove_text 'MEA4_B4'  // Marty sent you huh? OK, I'm gonna show that creep the meaning of the word business.
	end
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT4
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT4
	gosub @CHECK_LOVER_RANGE_STATUS
end //while

0164: disable_marker $RADAR_BLIP_COORD2_MEAT4 
02A3: toggle_widescreen 1 
01B4: set_player $PLAYER_CHAR controllable 0 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
01E0: clear_leader $LOANSHARK_MEAT4 
039E: set_char_cant_be_dragged_out $LOANSHARK_MEAT4 to 0 
01D3: actor $LOANSHARK_MEAT4 leave_car $CAR_MEAT4

while 00DB:   is_char_in_car $LOANSHARK_MEAT4 car $CAR_MEAT4 
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT4
end //while

0211: actor $LOANSHARK_MEAT4 walk_to 1209.563 -791.0 
015F: set_camera_position 1218.063 -795.0 16.0 0.0 rotation 0.0 0.0 
0160: point_camera 1204.563 -785.6875 13.875 switchstyle JUMP_CUT
0395: clear_area 1 at 1212.0 -792.0 range 14.0 10.0 
03CF: load_wav 'MF3_B' 
0006: 17@ = 0 

// Waiting for the blokes to get to the meat grinding area

while true
	if or
		8038:   not  $FLAG_LOANSHARK_IN_AREA == 1
		83D0:   not wav_loaded
	jf break
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
	if and
		0038:   $FLAG_LOANSHARK_IN_AREA == 0
		00ED:   actor $LOANSHARK_MEAT4 #NULL 1209.563 -791.0 radius 0.5 0.5 
	then
		0004: $FLAG_LOANSHARK_IN_AREA = 1 
	end
	if and
		0029:   17@ >= 25000 
		8038:   not  $FLAG_LOANSHARK_IN_AREA == 1
	then
		00A1: set_char_coordinates $LOANSHARK_MEAT4 to 1209.563 -791.0 13.6875
		goto @LOANSHARK_GOT_STUCK
	end
end //while

:LOANSHARK_GOT_STUCK

009F: char_set_idle $LOANSHARK_MEAT4 
020E: actor $LOANSHARK_MEAT4 look_at_actor $OWNER_MEAT4 
03D1: play_wav 
00BC: print_now 'MEA4_B5' duration 10000 ms flag 1  // Carl, hi! i eerr, I need more time to get your money.

while 83D2:   not wav_ended
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
end //while

03D5: remove_text 'MEA4_B5'  // Carl, hi! i eerr, I need more time to get your money.
03CF: load_wav 'MF3_B1' 
020E: actor $LOANSHARK_MEAT4 look_at_actor $OWNER_MEAT4 

while 83D0:   not wav_loaded
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
end //while

020E: actor $LOANSHARK_MEAT4 look_at_actor $OWNER_MEAT4 
03D1: play_wav 
00BC: print_now 'MEA4_B7' duration 20000 ms flag 1  // but if you just step into my office...

while 83D2:   not wav_ended
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
end //while

020E: actor $LOANSHARK_MEAT4 look_at_actor $OWNER_MEAT4 
03D5: remove_text 'MEA4_B7'  // but if you just step into my office...
03CF: load_wav 'MF3_C' 

while 83D0:   not wav_loaded
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
end //while

020E: actor $LOANSHARK_MEAT4 look_at_actor $OWNER_MEAT4 
03D1: play_wav 
00BC: print_now 'MEA4_B6' duration 10000 ms flag 1  // It's far too late for that Marty. You had your chance, but now I'm taking over the business...

while 83D2:   not wav_ended
	wait 0 ms
	gosub @CHECK_LOVER_STATUS
	gosub @CHECK_OWNER_STATUS
end //while

03D5: remove_text 'MEA4_B6'  // It's far too late for that Marty. You had your chance, but now I'm taking over the business...
01B2: give_actor $LOANSHARK_MEAT4 weapon WEAPONTYPE_SHOTGUN ammo 2 
01C9: actor $LOANSHARK_MEAT4 kill_actor $OWNER_MEAT4 
0006: 16@ = 0 

// Waiting for the owner to be killed

while 8038:   not  $FLAG_OWNER_DEAD_MEAT4 == 1
	wait 0 ms
	if
		03D2:   wav_ended
	then
		03D5: remove_text 'MEA4_B6'  // It's far too late for that Marty. You had your chance, but now I'm taking over the business...
	end
	if
		0118:   actor $OWNER_MEAT4 dead
	then
		0004: $FLAG_OWNER_DEAD_MEAT4 = 1 
	end
	gosub @CHECK_LOVER_STATUS
	if and
		0019:   16@ > 10000 
		8118:   not actor $OWNER_MEAT4 dead
	then
		0004: $FLAG_OWNER_DEAD_MEAT4 = 1 
	end
end //while

if
	03D2:   wav_ended
then
	03D5: remove_text 'MEA4_B6'  // It's far too late for that Marty. You had your chance, but now I'm taking over the business...
end

02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
02EB: restore_camera_jumpcut 

goto @MISSION_PASSED_MEAT4

/////////////////////////////////////////

:CHECK_VEHICLE_STATUS_MEAT4
if
	0119:   car $CAR_MEAT4 wrecked
then
	00BC: print_now 'WRECKED' duration 5000 ms flag 1  // ~r~The vehicle is wrecked!
	goto @MISSION_FAILED_MEAT4
end
if and
	01F4:   car $CAR_MEAT4 flipped 
	01C1:   car $CAR_MEAT4 stopped 
then
	00BC: print_now 'UPSIDE' duration 5000 ms flag 1  // ~r~You flipped your wheels!
	goto @MISSION_FAILED_MEAT4
end
return

/////////////////////////////////////////

:CHECK_LOVER_STATUS
if
	0118:   actor $LOANSHARK_MEAT4 dead
then
	00BC: print_now 'MEA4_1' duration 5000 ms flag 1  // ~r~Carlos is dead!
	goto @MISSION_FAILED_MEAT4
end
return

/////////////////////////////////////////

:CHECK_OWNER_STATUS
if
	0118:   actor $OWNER_MEAT4 dead
then
	00BC: print_now 'MEA4_2' duration 5000 ms flag 1  // ~r~Marty Chonks is dead!
	goto @MISSION_FAILED_MEAT4
end
return

/////////////////////////////////////////

:CHECK_IN_VEHICLE_STATUS_MEAT4
if and
	80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT4 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT4 == 0
then
	00BC: print_now 'IN_VEH' duration 5000 ms flag 1  // ~g~Hey! Get back in the vehicle!
	0186: $RADAR_BLIP_CAR_MEAT4 = create_marker_above_car $CAR_MEAT4
	if
		0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
	then
		0164: disable_marker $RADAR_BLIP_LOANSHARK_MEAT4
	else
		0164: disable_marker $RADAR_BLIP_COORD2_MEAT4
	end
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT4 = 1 
	0004: $BLOB_FLAG = 0
end
if and
	00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT4 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT4 == 1
then
	0164: disable_marker $RADAR_BLIP_CAR_MEAT4
	if
		0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
	then 
		0187: $RADAR_BLIP_LOANSHARK_MEAT4 = create_marker_above_actor $LOANSHARK_MEAT4
	else
		018A: $RADAR_BLIP_COORD2_MEAT4 = create_checkpoint_at 1217.0 -794.0 -100.0
	end
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT4 = 0 
	0004: $BLOB_FLAG = 1
end
return

/////////////////////////////////////////

:CHECK_LOVER_RANGE_STATUS
if
	80FB:   not player $PLAYER_CHAR 0 $LOANSHARK_MEAT4 radius 30.0 30.0 30.0 
then
	00BC: print_now 'MEA4_3' duration 5000 ms flag 1  // ~r~You have left Carlos the loan shark behind!
	goto @MISSION_FAILED_MEAT4
end
return

/////////////////////////////////////////

// Mission Failed

:MISSION_FAILED_MEAT4
00BA: print_big 'M_FAIL' duration 5000 ms style 1  // MISSION FAILED!
if
	8118:   not actor $OWNER_MEAT4 dead 
then
	034F: destroy_actor_with_fade $OWNER_MEAT4 
end
if
	8118:   not actor $LOANSHARK_MEAT4 dead
then
	034F: destroy_actor_with_fade $LOANSHARK_MEAT4
end
goto @MISSION_END_MEAT4

/////////////////////////////////////////

// Mission Passed

:MISSION_PASSED_MEAT4
01E3: text_1number_styled 'M_PASS' number 4000 duration 5000 ms style 1  // MISSION PASSED! $~1~
0318: set_latest_mission_passed 'MEA4'  // 'HER LOVER'
030C: set_mission_points += 1 
0394: play_mission_passed_music 1 
0109: player $PLAYER_CHAR money += 4000 
0004: $HER_LOVER_COMPLETED = 1 
0110: clear_player $PLAYER_CHAR wanted_level 
return 

/////////////////////////////////////////

// Mission Cleanup

:MISSION_CLEANUP_MEAT4
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_MARTY = 0 
0249: release_model #FAN_MAN2 
0249: release_model #B_MAN2 
0249: release_model #STALLION 
0164: disable_marker $RADAR_BLIP_LOANSHARK_MEAT4 
0164: disable_marker $RADAR_BLIP_CAR_MEAT4 
0164: disable_marker $RADAR_BLIP_COORD2_MEAT4 
00D8: mission_has_finished 
return 
