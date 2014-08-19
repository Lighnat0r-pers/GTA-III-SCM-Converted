// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************
// ***********************************Meat Factory Mission 3**********************************
// *****************************************"The Wife"****************************************
// *******************************************************************************************
// *******************************************************************************************

:M17_THEWIFE

03A4: name_thread 'MEAT3' 

gosub @MISSION_START_MEAT3
if
	0112:   has_deatharrest_been_executed
then
	gosub @MISSION_FAILED_MEAT3
end

:MISSION_END_MEAT3
gosub @MISSION_CLEANUP_MEAT3
end_thread

// ****************************************Mission Start************************************

:MISSION_START_MEAT3
0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_MARTY = 1 
0317: increment_mission_attempts 
0001: wait 0 ms 
0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 = 0 
0004: $FLAG_WIFE_IN_AREA = 0 
0004: $BLOB_FLAG = 1 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 0

// *****************************************START OF CUTSCENE*******************************

03DE: set_ped_density_multiplier 0.0 
042B: clear_peds_from_cube 1164.25 -888.8125 10.0 1291.75 -811.6875 20.0 
02E4: load_cutscene_data 'MT_PH3' 
0244: set_cutscene_pos 1223.875 -839.375 13.9375 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
016A: fade 1 for 1500 ms 
03AF: set_streaming 1 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime 

// Displays cutscene text

while 001A:   2000 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA3_A' time 10000 flag 1  // The business is going to go under unless I get hold of some serious cash soon.
while 001A:   6470 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA3_B' time 10000 flag 1  // My wife has an insurance policy and all she's ever been to me is a hole in my pocket.
while 001A:   11321 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA3_C' time 10000 flag 1  // I've left a car in the usual place.
while 001A:   13506 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA3_D' time 10000 flag 1  // Go and pick up my wife from Classic Nails and bring her back to the factory.
while 001A:   17471 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
03D5: remove_text 'MEA3_D'  // Go and pick up my wife from Classic Nails and bring her back to the factory.
while 001A:   18333 > $CUT_SCENE_TIME
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

0247: request_model #FEMALE02 
0247: request_model #ESPERANT 
03CF: load_wav 'MF2_A' 

while true
	if or
		8248:   not model #FEMALE02 available 
		8248:   not model #ESPERANT available 
		83D0:   not wav_loaded 
	jf break
	wait 0 ms
end //while

00A5: create_car #ESPERANT at 1190.0 -796.0 13.75 store_to $CAR_MEAT3 
0175: set_car $CAR_MEAT3 z_angle_to 300.0 
0186: $RADAR_BLIP_CAR_MEAT3 = create_marker_above_car $CAR_MEAT3

// waiting for the player to get into the car

while 80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT3
end //while

0164: disable_marker $RADAR_BLIP_CAR_MEAT3 
00BC: print_now 'MEA3_B3' time 7000 flag 1  // ~g~Go and collect Mrs. Chonks
009A: create_char PEDTYPE_CIVFEMALE model #FEMALE02 at 1064.0 -378.0 13.875 store_to $WIFE_MEAT3 
01ED: clear_actor $WIFE_MEAT3 threat_search 
01BE: set_actor $WIFE_MEAT3 to_look_at_spot 1059.0 -378.0 100.0 
0187: $RADAR_BLIP_WIFE_MEAT3 = create_marker_above_actor $WIFE_MEAT3 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
039E: set_char_cant_be_dragged_out $WIFE_MEAT3 to 1

// Waiting for the player to be in the area

while true
	if or
		80EB:   not player $PLAYER_CHAR 0 $WIFE_MEAT3 radius 8.0 8.0 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
	jf break
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT3
end //while

// tells the wife to get into players car

01D4: actor $WIFE_MEAT3 go_to_car $CAR_MEAT3 and_enter_it_as_a_passenger

while true
	if or
		80DB:   not is_char_in_car $WIFE_MEAT3 car $CAR_MEAT3 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
	jf break
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT3
	gosub @CHECK_WIFE_RANGE_STATUS
end //while

0164: disable_marker $RADAR_BLIP_WIFE_MEAT3 
03D1: play_wav 
00BC: print_now 'MEA3_B4' time 7000 flag 1  // Marty wants to see me? Well it better be quick because I have to get my hair done.
018A: $RADAR_BLIP_COORD2_MEAT3 = create_checkpoint_at 1205.688 -789.1875 -100.0 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 2
if
	03D2:   wav_ended
then
	03D5: remove_text 'MEA3_B4'  // Marty wants to see me? Well it better be quick because I have to get my hair done.
end

0004: $BLOB_FLAG = 1

// waiting for the wife to be at the factory

while true
	if or
		8103:   not actor $WIFE_MEAT3 stopped_near_point_in_car 1205.688 -789.1875 13.875 radius 4.0 4.0 6.0 sphere $BLOB_FLAG 
		80DB:   not is_char_in_car $WIFE_MEAT3 car $CAR_MEAT3 
	jf break
	wait 0 ms
	if
		03D2:   wav_ended
	then
		03D5: remove_text 'MEA3_B4'  // Marty wants to see me? Well it better be quick because I have to get my hair done.
	end
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT3
	gosub @CHECK_WIFE_RANGE_STATUS
end //while

0164: disable_marker $RADAR_BLIP_COORD2_MEAT3 
02A3: toggle_widescreen 1 
01B4: set_player $PLAYER_CHAR controllable 0 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
039E: set_char_cant_be_dragged_out $WIFE_MEAT3 to 0 
01D3: actor $WIFE_MEAT3 leave_car $CAR_MEAT3

while 00DB:   is_char_in_car $WIFE_MEAT3 car $CAR_MEAT3
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
end //while

0211: actor $WIFE_MEAT3 walk_to 1204.188 -801.875 
015F: set_camera_position 1201.75 -784.6875 17.0 0.0 rotation 0.0 0.0 
0160: point_camera 1204.375 -802.6875 15.0 switchstyle JUMP_CUT
0395: clear_area 1 at 1200.75 -799.25 range 14.0 10.0 
0006: 17@ = 0

// Waiting for the wifes to get to the meat grinding area

while 8038:   not  $FLAG_WIFE_IN_AREA == 1
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
	if and
		0038:   $FLAG_WIFE_IN_AREA == 0
		00ED:   actor $WIFE_MEAT3 0 1204.188 -801.875 radius 0.5 0.5
	then
		0004: $FLAG_WIFE_IN_AREA = 1 
	end
	if and
		0029:   17@ >= 25000 
		8038:   not  $FLAG_WIFE_IN_AREA == 1
	then
		00A1: set_char_coordinates $WIFE_MEAT3 to 1204.188 -801.875 13.6875
		goto @WIFE_STUCK1
	end
end //while

:WIFE_STUCK1

// opens the door

while 834D:   not rotate_object $DOGFOOD_DOOR from_angle 135.0 to 5.0 collision_check 0
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
end //while

0211: actor $WIFE_MEAT3 walk_to 1205.875 -805.75 
0006: 17@ = 0 

while 80FF:   not actor $WIFE_MEAT3 #NULL 1205.875 -805.75 14.0 radius 1.0 1.0 1.0 
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
	if and
		0029:   17@ >= 25000 
		80FF:   not actor $WIFE_MEAT3 #NULL 1205.875 -805.75 14.0 radius 1.0 1.0 1.0 
	then
		034F: destroy_actor_with_fade $WIFE_MEAT3
		goto @MISSION_BLOKE_STUCK_MEAT3
	end
end //while

// Shuts the door

:MISSION_BLOKE_STUCK_MEAT3

03CF: load_wav 'MF4_C' 

while true
	if or
		834D:   not rotate_object $DOGFOOD_DOOR from_angle 45.0 to 5.0 collision_check 0 
		83D0:   not wav_loaded
	jf break
	wait 0 ms
	gosub @CHECK_WIFE_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT3
end //while

02EB: restore_camera_jumpcut 
034F: destroy_actor_with_fade $WIFE_MEAT3 
03D1: play_wav 

while 83D2:   not wav_ended
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT3
end //while

02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
00BC: print_now 'MEA3_B6' time 5000 flag 1  // ~g~Take the car and dump it into the sea, this will get rid of any evidence.
if
	0119:   car $CAR_MEAT3 wrecked
then
	00BC: print_now 'MEA3_2' time 5000 flag 1  // ~r~You were supposed to dump the vehicle in the water!
	goto @MISSION_FAILED_MEAT3
end
if and
	01F4:   car $CAR_MEAT3 flipped 
	01C1:   car $CAR_MEAT3 stopped 
then
	00BC: print_now 'UPSIDE' time 5000 flag 1  // ~r~You flipped your wheels!
	goto @MISSION_FAILED_MEAT3
end

// waiting for the car to be dumped in the water

while 82BF:   not car $CAR_MEAT3 sunk
	wait 0 ms
	if
		0119:   car $CAR_MEAT3 wrecked
	then
		if 
			82BF:   not car $CAR_MEAT3 sunk 
		then
			00BC: print_now 'WRECKED' time 5000 flag 1  // ~r~The vehicle is wrecked!
			goto @MISSION_FAILED_MEAT3
		end
	else
		if and
			80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
			0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 == 0
		then
			0186: $RADAR_BLIP_CAR_MEAT3 = create_marker_above_car $CAR_MEAT3 
			0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 = 1 
		end
		if and
			00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
			0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 == 1 
		then
			0164: disable_marker $RADAR_BLIP_CAR_MEAT3 
			00BC: print_now 'MEA3_B6' time 5000 flag 1  // ~g~Take the car and dump it into the sea, this will get rid of any evidence.
			0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 = 0 
		end
	end
end //while

goto @MISSION_PASSED_MEAT3


/////////////////////////////////////////

:CHECK_VEHICLE_STATUS_MEAT3
if
	0119:   car $CAR_MEAT3 wrecked
then
	00BC: print_now 'WRECKED' time 5000 flag 1  // ~r~The vehicle is wrecked!
	goto @MISSION_FAILED_MEAT3
end
if and
	01F4:   car $CAR_MEAT3 flipped 
	01C1:   car $CAR_MEAT3 stopped 
then
	00BC: print_now 'UPSIDE' time 5000 flag 1  // ~r~You flipped your wheels!
	goto @MISSION_FAILED_MEAT3
end
return

/////////////////////////////////////////

:CHECK_WIFE_STATUS
if
	0118:   actor $WIFE_MEAT3 dead
then
	00BC: print_now 'MEA3_1' time 5000 flag 1  // ~r~The wife's dead!
	goto @MISSION_FAILED_MEAT3
end
return

/////////////////////////////////////////

:CHECK_IN_VEHICLE_STATUS_MEAT3
if and
	80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 == 0
then
	00BC: print_now 'IN_VEH' time 5000 flag 1  // ~g~Hey! Get back in the vehicle!
	0186: $RADAR_BLIP_CAR_MEAT3 = create_marker_above_car $CAR_MEAT3 
	if
		0038: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
	then
		0164: disable_marker $RADAR_BLIP_WIFE_MEAT3
	else
		 0164: disable_marker $RADAR_BLIP_COORD2_MEAT3 
	end
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 = 1 
	0004: $BLOB_FLAG = 0
end
if and
	00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT3 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 == 1
then
	0164: disable_marker $RADAR_BLIP_CAR_MEAT3
	if
		0038: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
	then 
		0187: $RADAR_BLIP_WIFE_MEAT3 = create_marker_above_actor $WIFE_MEAT3 
	else
		018A: $RADAR_BLIP_COORD2_MEAT3 = create_checkpoint_at 1205.688 -789.1875 -100.0 
	end
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT3 = 0 
	0004: $BLOB_FLAG = 1
end
return

/////////////////////////////////////////

:CHECK_WIFE_RANGE_STATUS
if
	80FB:   not player $PLAYER_CHAR 0 $WIFE_MEAT3 radius 30.0 30.0 30.0 
then
	00BC: print_now 'MEA3_3' time 5000 flag 1  // ~r~You have left his wife behind!
	goto @MISSION_FAILED_MEAT3
end
return

/////////////////////////////////////////

// Mission Failed

:MISSION_FAILED_MEAT3
00BA: print_big 'M_FAIL' time 5000 style 1  // MISSION FAILED!
goto @MISSION_END_MEAT3

/////////////////////////////////////////

// Mission Passed

:MISSION_PASSED_MEAT3
01E3: text_1number_styled 'M_PASS' number 2000 time 5000 style 1  // MISSION PASSED! $~1~
0318: set_latest_mission_passed 'MEA3'  // 'THE WIFE'
030C: progress_made = 1 
0394: play_mission_passed_music 1 
0109: player $PLAYER_CHAR money += 3000 
0004: $THE_WIFE_COMPLETED = 1 
0110: clear_player $PLAYER_CHAR wanted_level 
004F: create_thread @MEAT_MISSION4_LOOP 
0051: return

/////////////////////////////////////////

// Mission Cleanup

:MISSION_CLEANUP_MEAT3
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_MARTY = 0 
034F: destroy_actor_with_fade $WIFE_MEAT3 
0249: release_model #FEMALE02 
0249: release_model #ESPERANT 
0164: disable_marker $RADAR_BLIP_WIFE_MEAT3 
0164: disable_marker $RADAR_BLIP_CAR_MEAT3 
0164: disable_marker $RADAR_BLIP_COORD2_MEAT3 
00D8: mission_has_finished 
0051: return 
