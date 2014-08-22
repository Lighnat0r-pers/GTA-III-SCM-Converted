// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************
// ***********************************Meat Factory Mission 2**********************************
// *****************************************"The Thieves"*************************************
// *******************************************************************************************
// *******************************************************************************************

:M16_THETHIEVES

03A4: name_thread 'MEAT2'

gosub @MISSION_START_MEAT2
if
	0112:   has_deatharrest_been_executed 
then
	gosub @MISSION_FAILED_MEAT2
end

:MISSION_END_MEAT2
gosub @MISSION_CLEANUP_MEAT2
end_thread

// ****************************************Mission Start************************************

:MISSION_START_MEAT2
0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_MARTY = 1 
0317: increment_mission_attempts 
0001: wait 0 ms 
0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT2 = 0 
0004: $FLAG_VICTIM1_IN_AREA = 0 
0004: $FLAG_VICTIM2_IN_AREA = 0 
0004: $FLAG_CAR_IN_AREA_MEAT2 = 0 
0004: $BLOB_FLAG = 1 
0004: $FLAG_REMOVE_BLIP1_MEAT2 = 0 
0004: $FLAG_REMOVE_BLIP2_MEAT2 = 0 
0004: $FLAG_CAR_IN_ZONE2_MEAT2 = 0 
0004: $FLAG_PLAYER_HAD_OUT_CAR_MESSAGE_MEAT2 = 0
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 0

// *******************************START OF CUTSCENE*****************************************

03DE: set_ped_density_multiplier 0.0 
042B: clear_peds_from_cube 1164.25 -888.8125 10.0 1291.75 -811.6875 20.0 
02E4: load_cutscene_data 'MT_PH2' 
0244: set_cutscene_pos 1223.875 -839.375 13.9375 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
016A: fade 1 for 1500 ms 
03AF: set_streaming 1 
02E7: start_cutscene 

// Displays cutscene text

02E8: $CUT_SCENE_TIME = cutscenetime 

while 001A:   2000 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_A' duration 10000 ms flag 1  // I hired some thieves to break into my apartment...
while 001A:   4424 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_B' duration 10000 ms flag 1  // and steal some stuff so I could claim on the insurance as you do.
while 001A:   8124 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_C' duration 10000 ms flag 1  // The thieving bastards are threatening to tell the insurance company,
while 001A:   10996 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_D' duration 10000 ms flag 1  // if I don't give them a cut.
while 001A:   12362 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_E' duration 10000 ms flag 1  // Can you believe it?
while 001A:   13728 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_F' duration 10000 ms flag 1  // I've left a car inside the factory gates.
while 001A:   16082 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_G' duration 10000 ms flag 1  // Use it to go and pick them up from their turf in the Red Light district.
while 001A:   19591 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA2_H' duration 10000 ms flag 1  // Then bring 'em back to the factory so I can make 'em see Marty's point of view.
while 001A:   24155 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
03D5: remove_text 'MEA2_H'  // Then bring 'em back to the factory so I can make 'em see Marty's point of view.
while 001A:   25233 > $CUT_SCENE_TIME
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

// **************************************END OF CUTSCENE************************************

0247: request_model #CRIMINAL01 
0247: request_model #CRIMINAL02 
0247: request_model #SENTINEL 

while true
	if or
		8248:   not model #CRIMINAL01 available 
		8248:   not model #CRIMINAL02 available 
		8248:   not model #SENTINEL available
	jf break
	wait 0 ms
end

00A5: $CAR_MEAT2 = create_car #SENTINEL at 1190.0 -796.0 13.75
0175: set_car $CAR_MEAT2 z_angle_to 300.0 
0186: $RADAR_BLIP_CAR_MEAT2 = create_marker_above_car $CAR_MEAT2

// waiting for the player to get into the car

while 80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT2
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT2
end

0164: disable_marker $RADAR_BLIP_CAR_MEAT2 
00BC: print_now 'MEA2_B3' duration 7000 ms flag 1  // ~g~Go and meet the thieves.
009A: $VICTIM1_MEAT2 = create_char PEDTYPE_CIVMALE model #CRIMINAL01 at 869.0 -611.0 -100.0
01ED: clear_actor $VICTIM1_MEAT2 threat_search 
01BE: set_actor $VICTIM1_MEAT2 to_look_at_spot 869.0 -615.0 -100.0 
039E: set_char_cant_be_dragged_out $VICTIM1_MEAT2 to 1 
0187: $RADAR_BLIP_PED1_MEAT2 = create_marker_above_actor $VICTIM1_MEAT2 

009A: $VICTIM2_MEAT2 = create_char PEDTYPE_CIVMALE model #CRIMINAL02 at 871.0 -612.0 -100.0
01ED: clear_actor $VICTIM2_MEAT2 threat_search 
01BE: set_actor $VICTIM2_MEAT2 to_look_at_spot 869.0 -615.0 -100.0 
039E: set_char_cant_be_dragged_out $VICTIM2_MEAT2 to 1 
0187: $RADAR_BLIP_PED2_MEAT2 = create_marker_above_actor $VICTIM2_MEAT2 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1

// waiting for the player to get to the creditors

while true
	if or
		80EB:   not player $PLAYER_CHAR 0 $VICTIM1_MEAT2 radius 8.0 8.0 
		80EB:   not player $PLAYER_CHAR 0 $VICTIM2_MEAT2 radius 8.0 8.0 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT2 
	jf break
	wait 0 ms
	gosub @CHECK_THIEVES_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT2
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT2
end //while

// tells the two thieves to get into the car

01D4: actor $VICTIM1_MEAT2 go_to_car $CAR_MEAT2 and_enter_it_as_a_passenger 
01D4: actor $VICTIM2_MEAT2 go_to_car $CAR_MEAT2 and_enter_it_as_a_passenger 

while true
	if or
		80DB:   not is_char_in_car $VICTIM1_MEAT2 car $CAR_MEAT2 
		80DB:   not is_char_in_car $VICTIM2_MEAT2 car $CAR_MEAT2 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT2
	jf break
	wait 0 ms
	gosub @CHECK_THIEVES_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT2
	gosub @CHECK_THIEVES_RANGE_STATUS
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT2
	if and
		00DB:   is_char_in_car $VICTIM1_MEAT2 car $CAR_MEAT2 
		0038:   $FLAG_REMOVE_BLIP1_MEAT2 == 0 	
	then
		0164: disable_marker $RADAR_BLIP_PED1_MEAT2 
		0004: $FLAG_REMOVE_BLIP1_MEAT2 = 1
	end
	if and
		00DB:   is_char_in_car $VICTIM2_MEAT2 car $CAR_MEAT2 
		0038:   $FLAG_REMOVE_BLIP2_MEAT2 == 0 	
	then
		0164: disable_marker $RADAR_BLIP_PED2_MEAT2 
		0004: $FLAG_REMOVE_BLIP2_MEAT2 = 1
	end
end //while

0164: disable_marker $RADAR_BLIP_PED1_MEAT2 
0164: disable_marker $RADAR_BLIP_PED2_MEAT2 
00BC: print_now 'MEA2_B4' duration 7000 ms flag 1  // ~g~Take them to the Bitch'n' Dog Food Factory
018A: $RADAR_BLIP_COORD2_MEAT2 = create_checkpoint_at 1205.688 -789.1875 -100.0 
0004: $BLOB_FLAG = 1 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 2

while true
	if or
		8103:   not actor $VICTIM1_MEAT2 stopped_near_point_in_car 1205.688 -789.1875 13.875 radius 4.0 4.0 6.0 sphere $BLOB_FLAG 
		8103:   not actor $VICTIM2_MEAT2 stopped_near_point_in_car 1205.688 -789.1875 13.875 radius 4.0 4.0 6.0 sphere 0 
		80DB:   not is_char_in_car $VICTIM1_MEAT2 car $CAR_MEAT2 
		80DB:   not is_char_in_car $VICTIM2_MEAT2 car $CAR_MEAT2
	jf break
	wait 0 ms
	gosub @CHECK_THIEVES_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT2
	gosub @CHECK_THIEVES_RANGE_STATUS
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT2
end //while

0164: disable_marker $RADAR_BLIP_COORD2_MEAT2 
02A3: toggle_widescreen 1 
01B4: set_player $PLAYER_CHAR controllable 0 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
039E: set_char_cant_be_dragged_out $VICTIM1_MEAT2 to 0 
01D3: actor $VICTIM1_MEAT2 leave_car $CAR_MEAT2 
039E: set_char_cant_be_dragged_out $VICTIM2_MEAT2 to 0 
01D3: actor $VICTIM2_MEAT2 leave_car $CAR_MEAT2 

while true
	if and
		00DB:   is_char_in_car $VICTIM1_MEAT2 car $CAR_MEAT2 
		00DB:   is_char_in_car $VICTIM2_MEAT2 car $CAR_MEAT2
	jf break
	wait 0 ms
	gosub @CHECK_THIEVES_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT2
end //while

0211: actor $VICTIM1_MEAT2 walk_to 1203.25 -801.6875 
0001: wait 500 ms

if
	0118:   actor $VICTIM2_MEAT2 dead
then
	00BC: print_now 'MEA2_2' duration 5000 ms flag 1  // ~r~A thief's dead!
	goto @MISSION_FAILED_MEAT2
end

0211: actor $VICTIM2_MEAT2 walk_to 1202.375 -800.0 
015F: set_camera_position 1201.75 -784.6875 17.0 0.0 rotation 0.0 0.0 
0160: point_camera 1204.375 -802.6875 15.0 switchstyle JUMP_CUT
0395: clear_area 1 at 1201.75 -799.6875 range 13.75 10.0 
0006: 17@ = 0

// Waiting for the blokes to get to the meat grinding area

while true
	if or
		8038:   not  $FLAG_VICTIM1_IN_AREA == 2 
		8038:   not  $FLAG_VICTIM2_IN_AREA == 2
	jf break
	wait 0 ms
	if
		834D:   not rotate_object $DOGFOOD_DOOR from_angle 135.0 to 5.0 collision_check 0 
	then
		01BB: store_object $DOGFOOD_DOOR position_to $DOGF00D_DOOR_X $DOGFOOD_DOOR_Y $DOGFOOD_DOOR_Z
	end
	gosub @CHECK_THIEVES_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT2
	if and
		0038:   $FLAG_VICTIM1_IN_AREA == 0 
		00ED:   actor $VICTIM1_MEAT2 #NULL 1203.25 -801.6875 radius 0.5 0.5
	then
		0004: $FLAG_VICTIM1_IN_AREA = 1 
		0211: actor $VICTIM1_MEAT2 walk_to 1209.375 -807.1875
	end
	if and
		0038:   $FLAG_VICTIM1_IN_AREA == 1 
		00FF:   actor $VICTIM1_MEAT2 #NULL 1209.375 -807.1875 14.0 radius 4.0 4.0 4.0 
	then
		0004: $FLAG_VICTIM1_IN_AREA = 2
	end
	if and
		0038:   $FLAG_VICTIM2_IN_AREA == 0 
		00ED:   actor $VICTIM2_MEAT2 #NULL 1202.375 -800.0 radius 0.5 0.5 
	then
		0004: $FLAG_VICTIM2_IN_AREA = 1 
		0211: actor $VICTIM2_MEAT2 walk_to 1209.375 -807.1875
	end
	if and
		0038:   $FLAG_VICTIM2_IN_AREA == 1 
		00FF:   actor $VICTIM2_MEAT2 #NULL 1209.375 -807.1875 14.0 radius 4.0 4.0 4.0 
	then
		0004: $FLAG_VICTIM2_IN_AREA = 2
	end
	if
		0029:   17@ >= 30000
	then
		if or
			8038:   not  $FLAG_VICTIM1_IN_AREA == 2 
			8038:   not  $FLAG_VICTIM2_IN_AREA == 2
		then
			034F: destroy_actor_with_fade $VICTIM1_MEAT2 
			034F: destroy_actor_with_fade $VICTIM2_MEAT2
			goto @MISSION_BLOKE_STUCK
		end
	end
end //while

:MISSION_BLOKE_STUCK

03CF: load_wav 'MF4_B' 

// Shuts the door

while true
	if or
		834D:   not rotate_object $DOGFOOD_DOOR from_angle 45.0 to 5.0 collision_check 0 
		83D0:   not wav_loaded 
	jf break
	wait 0 ms
	gosub @CHECK_THIEVES_STATUS
	gosub @CHECK_VEHICLE_STATUS_MEAT2
end //while

02EB: restore_camera_jumpcut 
034F: destroy_actor_with_fade $VICTIM1_MEAT2 
034F: destroy_actor_with_fade $VICTIM2_MEAT2 
03D1: play_wav

while 83D2:   not wav_ended 
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT2
end

02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
00BD: print_soon 'MEA2_B6' duration 5000 ms flag 1  // ~g~Get the car resprayed to get rid of any evidence.
02A8: $RADAR_BLIP_COORD3_MEAT2 = create_marker RADAR_SPRITE_SPRAY at 924.0 -361.0 10.0 
0329:   garage $PORTLAND_PAYNSPRAY_GARAGE respray_done
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 3

// waiting for the player to respray the car

while true
	if or
		8329:   not garage $PORTLAND_PAYNSPRAY_GARAGE respray_done 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT2 
		8199:   not player $PLAYER_CHAR 0 922.5625 -366.0625 928.5625 -354.25
	jf break
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT2
	gosub @CHECK_IN_VEHICLE_STATUS_MEAT2
end //while

0164: disable_marker $RADAR_BLIP_COORD3_MEAT2 
00BC: print_now 'MEA2_3' duration 5000 ms flag 1  // ~g~Bring the car back to the factory.
018A: $RADAR_BLIP_COORD4_MEAT2 = create_checkpoint_at 1195.563 -805.0 13.6875 
0004: $BLOB_FLAG = 1
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 4

while true
	if or
		81B0:   not car $CAR_MEAT2 stopped $BLOB_FLAG 1195.563 -805.0 13.6875 radius 4.0 4.0 4.0 
		00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT2 
	jf break
	wait 0 ms
	gosub @CHECK_VEHICLE_STATUS_MEAT2
	if
		01B0:   car $CAR_MEAT2 stopped 0 1195.563 -805.0 13.6875 radius 4.0 4.0 4.0 
	then
		if
			0038:   $FLAG_PLAYER_HAD_OUT_CAR_MESSAGE_MEAT2 == 0 
		then
			00BC: print_now 'OUT_VEH' duration 5000 ms flag 1  // ~g~Get out of the vehicle!
			0004: $FLAG_PLAYER_HAD_OUT_CAR_MESSAGE_MEAT2 = 1
		end
		0004: $FLAG_CAR_IN_ZONE2_MEAT2 = 1 
	else
		0004: $FLAG_CAR_IN_ZONE2_MEAT2 = 0 
		0004: $FLAG_PLAYER_HAD_OUT_CAR_MESSAGE_MEAT2 = 0
	end
	if
		0038:   $FLAG_CAR_IN_ZONE2_MEAT2 == 0 
	then
		gosub @CHECK_IN_VEHICLE_STATUS_MEAT2
	end
end //while

if
	8119:   not car $CAR_MEAT2 wrecked 
then
	0135: set_car $CAR_MEAT2 door_lock CARLOCK_LOCKED
end

0164: disable_marker $RADAR_BLIP_COORD4_MEAT2

goto @MISSION_PASSED_MEAT2

/////////////////////////////////////////////

:CHECK_VEHICLE_STATUS_MEAT2
if
	0119:   car $CAR_MEAT2 wrecked
then
	00BC: print_now 'WRECKED' duration 5000 ms flag 1  // ~r~The vehicle is wrecked!
	goto @MISSION_FAILED_MEAT2
end
if and
	01F4:   car $CAR_MEAT2 flipped 
	01C1:   car $CAR_MEAT2 stopped 
then
	00BC: print_now 'UPSIDE' duration 5000 ms flag 1  // ~r~You flipped your wheels!
	goto @MISSION_FAILED_MEAT2
end
return

/////////////////////////////////////////////

:CHECK_THIEVES_STATUS
if or
	0118:   actor $VICTIM1_MEAT2 dead 
	0118:   actor $VICTIM2_MEAT2 dead 
then
	00BC: print_now 'MEA2_2' duration 5000 ms flag 1  // ~r~A thief's dead!
	goto @MISSION_FAILED_MEAT2
end
return

/////////////////////////////////////////////

:CHECK_THIEVES_RANGE_STATUS
if or
	80FB:   not player $PLAYER_CHAR 0 $VICTIM1_MEAT2 radius 30.0 30.0 30.0 
	80FB:   not player $PLAYER_CHAR 0 $VICTIM2_MEAT2 radius 30.0 30.0 30.0
then
	00BC: print_now 'MEA2_4' duration 5000 ms flag 1  // ~r~You have left a thief behind!
	goto @MISSION_FAILED_MEAT2
end

/////////////////////////////////////////////

:CHECK_IN_VEHICLE_STATUS_MEAT2
if and
	80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT2 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT2 == 0 
then
	00BC: print_now 'IN_VEH' duration 5000 ms flag 1  // ~g~Hey! Get back in the vehicle!
	0186: $RADAR_BLIP_CAR_MEAT2 = create_marker_above_car $CAR_MEAT2
	if
		0038: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
	then
		0164: disable_marker $RADAR_BLIP_PED1_MEAT2
		0164: disable_marker $RADAR_BLIP_PED2_MEAT2
	else 
		if 
			0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 2
		then
			0164: disable_marker $RADAR_BLIP_COORD2_MEAT2
		else
			if 
				0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 3
			then
				0164: disable_marker $RADAR_BLIP_COORD3_MEAT2 
			else // ==4
				0164: disable_marker $RADAR_BLIP_COORD4_MEAT2 
			end
		end
	end
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT2 = 1 
	0004: $BLOB_FLAG = 0
end
if and
	00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT2 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT2 == 1
then
	if
		0038: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1
	then
		0187: $RADAR_BLIP_PED1_MEAT2 = create_marker_above_actor $VICTIM1_MEAT2
		0187: $RADAR_BLIP_PED2_MEAT2 = create_marker_above_actor $VICTIM2_MEAT2
	else
		if 
			0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 2
		then
			018A: $RADAR_BLIP_COORD2_MEAT2 = create_checkpoint_at 1205.688 -789.1875 -100.0
		else
			if 
				0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 3
			then
				02A8: $RADAR_BLIP_COORD3_MEAT2 = create_marker RADAR_SPRITE_SPRAY at 924.0 -361.0 10.0
			else // ==4
				018A: $RADAR_BLIP_COORD4_MEAT2 = create_checkpoint_at 1195.563 -805.0 13.6875
			end
		end
	end
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT2 = 0 
	0004: $BLOB_FLAG = 1
end
return

/////////////////////////////////////////////

// Mission Failed

:MISSION_FAILED_MEAT2

00BA: print_big 'M_FAIL' duration 5000 ms style 1  // MISSION FAILED!
goto @MISSION_END_MEAT2

/////////////////////////////////////////////

// Mission Passed

:MISSION_PASSED_MEAT2
01E3: text_1number_styled 'M_PASS' number 3000 duration 5000 ms style 1  // MISSION PASSED! $~1~
0318: set_latest_mission_passed 'MEA2'  // 'THE THIEVES'
030C: set_mission_points += 1 
0394: play_mission_passed_music 1 
0109: player $PLAYER_CHAR money += 2000 
0004: $THE_THIEVES_COMPLETED = 1 
0110: clear_player $PLAYER_CHAR wanted_level 
004F: create_thread @MEAT_MISSION3_LOOP 
return

/////////////////////////////////////////////

// Mission Cleanup

:MISSION_CLEANUP_MEAT2
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_MARTY = 0 
034F: destroy_actor_with_fade $VICTIM1_MEAT2 
034F: destroy_actor_with_fade $VICTIM2_MEAT2 
0249: release_model #CRIMINAL01 
0249: release_model #CRIMINAL02 
0249: release_model #SENTINEL 
0164: disable_marker $RADAR_BLIP_PED1_MEAT2 
0164: disable_marker $RADAR_BLIP_PED2_MEAT2 
0164: disable_marker $RADAR_BLIP_CAR_MEAT2 
0164: disable_marker $RADAR_BLIP_COORD2_MEAT2 
0164: disable_marker $RADAR_BLIP_COORD3_MEAT2 
0164: disable_marker $RADAR_BLIP_COORD4_MEAT2 
00D8: mission_has_finished 
return

/////////////////////////////////////////////
