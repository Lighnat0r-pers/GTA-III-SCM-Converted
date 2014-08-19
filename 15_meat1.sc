// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************
// ***********************************Meat Factory Mission 1**********************************
// *************************************"The Bank Manager"************************************
// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************

:M15_THECROOK

03A4: name_thread 'MEAT1'

gosub @MISSION_START_MEAT1
if
	0112: has_deatharrest_been_executed
then
	gosub @MISSION_FAILED_MEAT1
end

:MISSION_END_MEAT1
gosub @MISSION_CLEANUP_MEAT1
end_thread

// ****************************************Mission Start************************************

:MISSION_START_MEAT1
0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_MARTY = 1 
0317: increment_mission_attempts 
0001: wait 0 ms 
0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 = 0 
0004: $FLAG_BANKMANAGER_IN_AREA = 0 
0004: $FLAG_CAR_CRUSHED_MEAT1 = 0 
0004: $FLAG_CAR_IN_AREA_MEAT1 = 0 
0004: $FLAG_LEAVE_CAR_MESSAGE_MEAT1 = 0 
0004: $FLAG_DONT_DO_CAR_CHECK_MEAT1 = 0 
0004: $BLOB_FLAG = 1 
0004: $FLAG_PLAYER_HAD_CRUSHER_HELP_HM5 = 0 
0004: $FLAG_BANKMAN_MOVED_MEAT1 = 0 
03DE: set_ped_density_multiplier 0.0 
042B: clear_peds_from_cube 1164.25 -888.8125 10.0 1291.75 -811.6875 20.0 
02E4: load_cutscene_data 'MT_PH1' 
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
00BC: print_now 'MEA1_B' time 10000 flag 1  // The name's Chonks, Marty Chonks.
while 001A:   4424 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA1_C' time 10000 flag 1  // I run the Bitchin' Dog Food factory around the corner.
while 001A:   7668 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA1_D' time 10000 flag 1  // I got money troubles, but hey, who doesn't right?
while 001A:   9604 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA1_E' time 10000 flag 1  // I'm meeting my bank manager later.
while 001A:   12652 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA1_F' time 10000 flag 1  // He's a crooked bastard that keeps bumping up the loan repayments so he can cut a slice.
while 001A:   17740 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA1_G' time 10000 flag 1  // Take my car, pick him up and bring him back here.
while 001A:   21290 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
00BC: print_now 'MEA1_H' time 10000 flag 1  // I've got a little surprise for that blood sucking leech!!
while 001A:   24535 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime
end
03D5: remove_text 'MEA1_H'  // I've got a little surprise for that blood sucking leech!!
while 001A:   25666 > $CUT_SCENE_TIME
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

// ******************************************END OF CUTSCENE********************************

0247: request_model #B_MAN1 
0247: request_model #PEREN 
03CF: load_wav 'MF1_A'

while true
	if or
		8248:   not model #B_MAN1 available 
		8248:   not model #PEREN available 
		83D0:   not wav_loaded
	jf break
	wait 0 ms
end

00A5: create_car #PEREN at 1190.0 -796.0 13.75 store_to $CAR_MEAT1 
0175: set_car $CAR_MEAT1 z_angle_to 300.0 
0186: $RADAR_BLIP_CAR_MEAT1 = create_marker_above_car $CAR_MEAT1

// waiting for the player to get into the car

while 80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT1
	wait 0 ms
	gosub @CHECK_CAR_STATUS
end

0164: disable_marker $RADAR_BLIP_CAR_MEAT1 
00BC: print_now 'MEA1_B3' time 7000 flag 1  // ~g~Go and meet the Bank Manager.
009A: create_char PEDTYPE_CIVMALE model #B_MAN1 at 1039.0 -695.0 13.875 store_to $BANKMANAGER_MEAT1 
01ED: clear_actor $BANKMANAGER_MEAT1 threat_search 
01BE: set_actor $BANKMANAGER_MEAT1 to_look_at_spot 1042.0 -695.0 -100.0 
0187: $RADAR_BLIP_PED1_MEAT1 = create_marker_above_actor $BANKMANAGER_MEAT1 
039E: set_char_cant_be_dragged_out $BANKMANAGER_MEAT1 to 1 
0084: $MARKER_TO_DISABLE_IF_PLAYER_OUT_CAR = $RADAR_BLIP_PED1_MEAT1

// waiting for the player and the Bank manager to be in the same area

while true
	if or
		80EB:   not player $PLAYER_CHAR 0 $BANKMANAGER_MEAT1 radius 8.0 8.0 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT1
	jf break
	wait 0 ms
	gosub @CHECK_BANKMANAGER_DEAD
	gosub @CHECK_CAR_STATUS
	gosub @CHECK_PLAYER_IN_CAR
	gosub @RESTORE_BANKMANAGER_MARKER
end //while

// player is at the bank manager

01D4: actor $BANKMANAGER_MEAT1 go_to_car $CAR_MEAT1 and_enter_it_as_a_passenger 

// waiting for them both to be in the car 

while true
	if or
		80DB:   not is_char_in_car $BANKMANAGER_MEAT1 car $CAR_MEAT1 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT1
	jf break
	wait 0 ms
	gosub @CHECK_FAIL_CRITERIA
	gosub @RESTORE_BANKMANAGER_MARKER
end //while

0164: disable_marker $RADAR_BLIP_PED1_MEAT1 
03D1: play_wav 
00BC: print_now 'MEA1_B4' time 7000 flag 1  // Ah, Mr Chonks sent you did he. Let's go and pay the fellow a visit.
018A: $RADAR_BLIP_COORD2_MEAT1 = create_checkpoint_at 1205.688 -789.1875 -100.0 
0084: $MARKER_TO_DISABLE_IF_PLAYER_OUT_CAR = $RADAR_BLIP_COORD2_MEAT1
if
	03D2:   wav_ended 
then
	03D5: remove_text 'MEA1_B4'  // Ah, Mr Chonks sent you did he. Let's go and pay the fellow a visit.
end
0004: $BLOB_FLAG = 1 
while true
	if or
		8103:   not actor $BANKMANAGER_MEAT1 stopped_near_point_in_car 1205.688 -789.1875 14.75 radius 4.0 4.0 6.0 sphere $BLOB_FLAG 
		80DB:   not is_char_in_car $BANKMANAGER_MEAT1 car $CAR_MEAT1
	jf break
	wait 0 ms
	if
		03D2:   wav_ended 
	then
		03D5: remove_text 'MEA1_B4'  // Ah, Mr Chonks sent you did he. Let's go and pay the fellow a visit.
	end
	gosub @CHECK_FAIL_CRITERIA
	if and
		00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT1 
		0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 == 1
	then
		0164: disable_marker $RADAR_BLIP_CAR_MEAT1 
		018A: $RADAR_BLIP_COORD2_MEAT1 = create_checkpoint_at 1205.688 -789.1875 -100.0 
		0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 = 0 
		0004: $BLOB_FLAG = 1
	end
end //while

0164: disable_marker $RADAR_BLIP_COORD2_MEAT1 
02A3: toggle_widescreen 1 
01B4: set_player $PLAYER_CHAR controllable 0 
0395: clear_area 1 at 1201.75 -799.6875 range 13.75 5.0 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
039E: set_char_cant_be_dragged_out $BANKMANAGER_MEAT1 to 0 
01D3: actor $BANKMANAGER_MEAT1 leave_car $CAR_MEAT1

while 00DB:   is_char_in_car $BANKMANAGER_MEAT1 car $CAR_MEAT1
	wait 0 ms
	gosub @CHECK_BANKMANAGER_DEAD
	gosub @CHECK_CAR_STATUS
end //while

0211: actor $BANKMANAGER_MEAT1 walk_to 1204.188 -801.875 
015F: set_camera_position 1201.75 -784.6875 17.0 0.0 rotation 0.0 0.0 
0160: point_camera 1204.375 -802.6875 15.0 switchstyle JUMP_CUT
0395: clear_area 1 at 1200.75 -799.25 range 14.0 10.0 

// Waiting for the blokes to get to the meat grinding area

0006: 17@ = 0

while 8038:   not  $FLAG_BANKMANAGER_IN_AREA == 1
	wait 0 ms
	gosub @CHECK_BANKMANAGER_DEAD
	gosub @CHECK_CAR_STATUS
	if and
		0038:   $FLAG_BANKMANAGER_IN_AREA == 0
		00ED:   actor $BANKMANAGER_MEAT1 0 1204.188 -801.875 radius 0.5 0.5
	then
		0004: $FLAG_BANKMANAGER_IN_AREA = 1
	end
	if and
		0038:   $FLAG_BANKMAN_MOVED_MEAT1 == 0
		0029:   17@ >= 20000 
	then
		00A1: set_char_coordinates $BANKMANAGER_MEAT1 to 1204.188 -801.875 13.6875
		0004: $FLAG_BANKMAN_MOVED_MEAT1 = 1
	end
end //while

// opens the door

while 834D:   not rotate_object $DOGFOOD_DOOR from_angle 135.0 to 5.0 collision_check 0
	wait 0 ms
	gosub @CHECK_BANKMANAGER_DEAD
	gosub @CHECK_CAR_STATUS
end //while

0211: actor $BANKMANAGER_MEAT1 walk_to 1205.875 -805.75 
0006: 17@ = 0

while 80FF:   not actor $BANKMANAGER_MEAT1 #NULL 1205.875 -805.75 14.0 radius 1.0 1.0 1.0
	wait 0 ms
	gosub @CHECK_BANKMANAGER_DEAD
	gosub @CHECK_CAR_STATUS
	if and
		0029:   17@ >= 20000 
		80FF:   not actor $BANKMANAGER_MEAT1 #NULL 1205.875 -805.75 14.0 radius 1.0 1.0 1.0
	then
		034F: destroy_actor_with_fade $BANKMANAGER_MEAT1
		goto @BLOKE_GOT_STUCK_MEAT1
	end
end //while

// Shuts the door

:BLOKE_GOT_STUCK_MEAT1
03CF: load_wav 'MF4_A' 

while true
	if or
		834D:   not rotate_object $DOGFOOD_DOOR from_angle 45.0 to 5.0 collision_check 0 
		83D0:   not wav_loaded
	jf break
	wait 0 ms
	gosub @CHECK_BANKMANAGER_DEAD
	gosub @CHECK_CAR_STATUS
end //while

02EB: restore_camera_jumpcut 
034F: destroy_actor_with_fade $BANKMANAGER_MEAT1 
03D1: play_wav 

while 83D2:   not wav_ended
	wait 0 ms
	gosub @CHECK_CAR_STATUS
end //while

02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
00BD: print_soon 'MEA1_B6' time 5000 flag 1  // ~g~Take the car to the crusher to get rid of evidence, get out of the car and the crane will pick it up.
018A: $RADAR_BLIP_COORD3_MEAT1 = create_checkpoint_at 1138.0 52.0 -100.0 
0084: $MARKER_TO_DISABLE_IF_PLAYER_OUT_CAR = $RADAR_BLIP_COORD3_MEAT1

// waiting for the car to get to the area to be crushed

0004: $BLOB_FLAG = 1

while 81AC:   not car $CAR_MEAT1 stopped $BLOB_FLAG 1135.75 55.5 -1.0 1149.75 46.25 9.0
	wait 0 ms
	if
		0119:   car $CAR_MEAT1 wrecked
	then
		00BC: print_now 'MEA1_2' time 5000 flag 1  // ~r~You were told to crush the vehicle!
		goto @MISSION_FAILED_MEAT1
	else
		if
			01AC:   car $CAR_MEAT1 stopped 0 1135.75 55.5 -1.0 1149.75 46.25 9.0
		then
			if
				0038:   $FLAG_LEAVE_CAR_MESSAGE_MEAT1 == 0
			then
				00BC: print_now 'MEA1_3' time 5000 flag 1  // ~g~Get out of the car!
				0004: $FLAG_LEAVE_CAR_MESSAGE_MEAT1 = 1
			end
		else
			0004: $FLAG_LEAVE_CAR_MESSAGE_MEAT1 = 0 
			0004: $FLAG_DONT_DO_CAR_CHECK_MEAT1 = 0 
		end
	end
	if
		0038:   $FLAG_DONT_DO_CAR_CHECK_MEAT1 == 0
	then
		gosub @CHECK_PLAYER_IN_CAR
		if and
			00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT1 
			0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 == 1
		then
			0164: disable_marker $RADAR_BLIP_CAR_MEAT1 
			018A: $RADAR_BLIP_COORD3_MEAT1 = create_checkpoint_at 1138.0 52.0 -100.0
			0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 = 0 
			0004: $BLOB_FLAG = 1
		end
	end
	if
		0038:   $FLAG_PLAYER_HAD_CRUSHER_HELP_HM5 == 0
	then
		if and
			00E3:   player $PLAYER_CHAR 0 1140.25 50.0625 radius 20.0 20.0 
			00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT1
		then
			03E5: text_box 'CRUSH'  // Park in the marked area and exit your vehicle. The vehicle will then be crushed.
			0004: $FLAG_PLAYER_HAD_CRUSHER_HELP_HM5 = 1
		end
	end
	if and
		01F4:   car $CAR_MEAT1 flipped 
		01C1:   car $CAR_MEAT1 stopped
	then
		00BC: print_now 'UPSIDE' time 5000 flag 1  // ~r~You flipped your wheels!
		goto @MISSION_FAILED_MEAT1
	end
end //while

// waiting for the crane to pick up the car

0004: $BLOB_FLAG = 1

while 83A0:   not car $CAR_MEAT1 picked_up_by_crane 1120.0 46.0
	wait 0 ms
	if
		0119:   car $CAR_MEAT1 wrecked
	then
		00BC: print_now 'MEA1_2' time 5000 flag 1  // ~r~You were told to crush the vehicle!
		goto @MISSION_FAILED_MEAT1
	end
	if
		01AC:   car $CAR_MEAT1 stopped $BLOB_FLAG 1135.75 55.5 -1.0 1149.75 46.25 20.0
	then
		0004: $FLAG_CAR_IN_AREA_MEAT1 = 1
	else
		0004: $FLAG_CAR_IN_AREA_MEAT1 = 0
	end
	if
		0038:   $FLAG_CAR_IN_AREA_MEAT1 == 0
	then
		gosub @CHECK_PLAYER_IN_CAR
		if and
			00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT1 
			0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 == 1
		then
			0164: disable_marker $RADAR_BLIP_CAR_MEAT1 
			018A: $RADAR_BLIP_COORD3_MEAT1 = create_checkpoint_at 1138.0 52.0 -100.0
			0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 = 0 
			0004: $BLOB_FLAG = 1
		end
	end
	if and
		01F4:   car $CAR_MEAT1 flipped 
		01C1:   car $CAR_MEAT1 stopped
	then
		00BC: print_now 'UPSIDE' time 5000 flag 1  // ~r~You flipped your wheels!
		goto @MISSION_FAILED_MEAT1
	end
end //while

0164: disable_marker $RADAR_BLIP_COORD3_MEAT1

// waiting for the car to be crushed

while 0038:   $FLAG_CAR_CRUSHED_MEAT1 == 0
	wait 0 ms
	if
		0149:   car $CAR_MEAT1 crushed_by_car_crusher
	then
		0004: $FLAG_CAR_CRUSHED_MEAT1 = 1
		goto @MISSION_PASSED_MEAT1
	else
		if
			0119:   car $CAR_MEAT1 wrecked
		then
			00BC: print_now 'MEA1_2' time 5000 flag 1  // ~r~You were told to crush the vehicle!
			goto @MISSION_FAILED_MEAT1
		end
	end
end //while


// Mission Failed //////////////////

:MISSION_FAILED_MEAT1
00BA: print_big 'M_FAIL' time 5000 style 1  // MISSION FAILED!
goto @MISSION_END_MEAT1


// Mission Passed //////////////////

:MISSION_PASSED_MEAT1
01E3: text_1number_styled 'M_PASS' number 1000 time 5000 style 1  // MISSION PASSED! $~1~
0318: set_latest_mission_passed 'MEA1'  // 'THE CROOK'
030C: progress_made = 1 
0394: play_mission_passed_music 1 
0109: player $PLAYER_CHAR money += 1000 
0004: $THE_CROOK_COMPLETED = 1 
0110: clear_player $PLAYER_CHAR wanted_level 
004F: create_thread @MEAT_MISSION2_LOOP 
goto @MISSION_END_MEAT1


// Mission Cleanup /////////////////

:MISSION_CLEANUP_MEAT1
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_MARTY = 0 
034F: destroy_actor_with_fade $BANKMANAGER_MEAT1 
0249: release_model #B_MAN1 
0249: release_model #PEREN 
0164: disable_marker $RADAR_BLIP_PED1_MEAT1 
0164: disable_marker $RADAR_BLIP_CAR_MEAT1 
0164: disable_marker $RADAR_BLIP_COORD2_MEAT1 
0164: disable_marker $RADAR_BLIP_COORD3_MEAT1 
00D8: mission_has_finished 
0051: return

////////////////////////////////////

:CHECK_CAR_STATUS
if
	0119:   car $CAR_MEAT1 wrecked
then
	00BC: print_now 'WRECKED' time 5000 flag 1  // ~r~The vehicle is wrecked!
	goto @MISSION_FAILED_MEAT1
end
if and
	01F4:   car $CAR_MEAT1 flipped 
	01C1:   car $CAR_MEAT1 stopped
then
	00BC: print_now 'UPSIDE' time 5000 flag 1  // ~r~You flipped your wheels!
	goto @MISSION_FAILED_MEAT1
end
return

////////////////////////////////////

:CHECK_PLAYER_IN_CAR
if and
	80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_MEAT1 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 == 0
then
	00BC: print_now 'IN_VEH' time 5000 flag 1  // ~g~Hey! Get back in the vehicle!
	0186: $RADAR_BLIP_CAR_MEAT1 = create_marker_above_car $CAR_MEAT1 
	0164: disable_marker $MARKER_TO_DISABLE_IF_PLAYER_OUT_CAR 
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 = 1 
	0004: $BLOB_FLAG = 0
end
return

////////////////////////////////////

:CHECK_BANKMANAGER_DEAD
if
	0118:   actor $BANKMANAGER_MEAT1 dead
then
	00BC: print_now 'MEA1_1' time 5000 flag 1  // ~r~The Bank Manager's dead!
	goto @MISSION_FAILED_MEAT1
end
return

////////////////////////////////////

:RESTORE_BANKMANAGER_MARKER
if and
	00DC:   is_player_in_car $PLAYER_CHAR car $CAR_MEAT1 
	0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 == 1
then
	0164: disable_marker $RADAR_BLIP_CAR_MEAT1 
	0187: $RADAR_BLIP_PED1_MEAT1 = create_marker_above_actor $BANKMANAGER_MEAT1 
	0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_MEAT1 = 0 
	0004: $BLOB_FLAG = 1
end
return

////////////////////////////////////

:CHECK_FAIL_CRITERIA
gosub @CHECK_BANKMANAGER_DEAD
gosub @CHECK_CAR_STATUS
if
	80FB:   not player $PLAYER_CHAR 0 $BANKMANAGER_MEAT1 radius 30.0 30.0 30.0 
then
	00BC: print_now 'MEA1_4' time 5000 flag 1  // ~r~You have left the Bank Manager behind!
	goto @MISSION_FAILED_MEAT1
end
gosub @CHECK_PLAYER_IN_CAR
return
