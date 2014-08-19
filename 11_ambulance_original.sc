// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *********************************** Ambulance missions ********************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

:M11_PARAMEDIC
gosub @MISSION_START_AMBULANCE
gosub @AMBULANCE_FAILED

:MISSION_END_AMBULANCE
end_thread

// Variables for mission
//
//VAR_INT	ped_time_limit players_ambulance flag_got_range_message	ped_time_limit_temp	ped_counter	number_of_injured_peds brackets_var
///VAR_INT ambulance_health_last ambulance_health_now time_drop max_peds_in_car peds_in_car player_in_range_flag score_am bonus_time_flag
//VAR_INT saved_peds	hospital_blip first_rescue_flag	time_chunk first_chunk_flag	ambulance_level	time_chunk_in_secs car_full_flag paramedic_location
//VAR_INT drop_off_time_bonus	hospital_blip_flag random_scream mission_end_button_ambulance ped_sex_flag players_ambulance_health	saved_peds_this_go
//VAR_INT injured_ped_1 injured_ped_1_blip injured_ped_1_flag
//VAR_INT injured_ped_2 injured_ped_2_blip injured_ped_2_flag
//VAR_INT injured_ped_3 injured_ped_3_blip injured_ped_3_flag
//VAR_INT injured_ped_4 injured_ped_4_blip injured_ped_4_flag
//VAR_INT injured_ped_5 injured_ped_5_blip injured_ped_5_flag
//VAR_INT injured_ped_6 injured_ped_6_blip injured_ped_6_flag
//VAR_INT injured_ped_7 injured_ped_7_blip injured_ped_7_flag
//VAR_INT injured_ped_8 injured_ped_8_blip injured_ped_8_flag
//VAR_INT injured_ped_9 injured_ped_9_blip injured_ped_9_flag
//VAR_INT injured_ped_10 injured_ped_10_blip injured_ped_10_flag
//VAR_INT injured_ped_11 injured_ped_11_blip injured_ped_11_flag
//VAR_INT injured_ped_12 injured_ped_12_blip injured_ped_12_flag
////VAR_INT injured_ped_13 injured_ped_13_blip injured_ped_13_flag
////VAR_INT injured_ped_14 injured_ped_14_blip injured_ped_14_flag
////VAR_INT injured_ped_15 injured_ped_15_blip injured_ped_15_flag
//
//VAR_FLOAT random_x random_y	hospital_x hospital_y hospital_z
//VAR_FLOAT player1_a_x	player1_a_y player1_a_z	hospital_door_x	hospital_door_y
//VAR_FLOAT ped_coord_x ped_coord_y	ped_coord_z	sound_x sound_y sound_z
//VAR_FLOAT difference_x_float_a difference_y_float_a	sum_difference_a_xy	
//VAR_FLOAT players_distance_from_ped peds_distance_from_hospital ped_time_limit_float random_ped_heading	time_chunk_divider
//


// ****************************************Mission Start************************************

:MISSION_START_AMBULANCE
0004: $ONMISSION = 1 
0004: $ON_PARAMEDIC_MISSION = 1 
0001: wait 0 ms 
03A4: name_thread 'AMBULAN' 
0004: $PED_TIME_LIMIT = 0 
0004: $PLAYERS_AMBULANCE = 0 
0004: $FLAG_GOT_RANGE_MESSAGE = 0 
0004: $AMBULANCE_HEALTH_LAST = 0 
0004: $AMBULANCE_HEALTH_NOW = 0 
0004: $TIME_DROP = 0 
0004: $MAX_PEDS_IN_CAR = 0 
0004: $PEDS_IN_CAR = 0 
0004: $PLAYER_IN_RANGE_FLAG = 0 
if
	0038:	$MASTERDEBUG == 1
then
	0004: $NUMBER_OF_INJURED_PEDS = 1
else
	0004: $NUMBER_OF_INJURED_PEDS = 1
end
0004: $PED_COUNTER = 0 
0004: $INJURED_PED_1_FLAG = 0 
0004: $INJURED_PED_2_FLAG = 0 
0004: $INJURED_PED_3_FLAG = 0 
0004: $INJURED_PED_4_FLAG = 0 
0004: $INJURED_PED_5_FLAG = 0 
0004: $INJURED_PED_6_FLAG = 0 
0004: $INJURED_PED_7_FLAG = 0 
0004: $INJURED_PED_8_FLAG = 0 
0004: $INJURED_PED_9_FLAG = 0 
0004: $INJURED_PED_10_FLAG = 0 
0004: $INJURED_PED_11_FLAG = 0 
0004: $INJURED_PED_12_FLAG = 0 
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	0004: $INJURED_PED_13_FLAG = 0 
	0004: $INJURED_PED_14_FLAG = 0 
	0004: $INJURED_PED_15_FLAG = 0 
end
0004: $SAVED_PEDS = 0 
0004: $FIRST_RESCUE_FLAG = 0 
0004: $FIRST_CHUNK_FLAG = 0 
0005: $TIME_CHUNK_DIVIDER = 11.0 
0004: $AMBULANCE_LEVEL = 1 
0004: $TIME_CHUNK = 0 
0004: $TIME_CHUNK_IN_SECS = 0 
0004: $SCORE_AM = 0 
0004: $BONUS_TIME_FLAG = 0 
0004: $DROP_OFF_TIME_BONUS = 0 
0004: $HOSPITAL_BLIP_FLAG = 0 
0004: $MISSION_END_BUTTON_AMBULANCE = 0 
0004: $CAR_FULL_FLAG = 0 
0004: $SAVED_PEDS_THIS_GO = 0 
0004: $PARAMEDIC_LOCATION = 0 

00BC: print_now 'ATUTOR2' time 3000 flag 1  // ~g~Drive the patients to Hospital CAREFULLY. Each bump reduces their chances of survival.
0001: wait 3000 ms 
03C7: set_sensitivity_to_crime_to 0.5 

:MISSION_ROOT
01E5: text_1number_highpriority 'ALEVEL' $AMBULANCE_LEVEL flag 5000 time 4  // Paramedic Mission Level ~1~
014F: stop_timer $PED_TIME_LIMIT 
0004: $PED_TIME_LIMIT = 0 
03E6: remove_text_box 
if
	0038:   $GOT_SIREN_HELP_BEFORE == 0
then
	03E5: text_box 'SIREN_1'  // To turn on this vehicle's sirens tap the ~h~~k~~VEHICLE_HORN~ button~w~.
	0004: $GOT_SIREN_HELP_BEFORE = 1 
end
while 001C:   $NUMBER_OF_INJURED_PEDS > $PED_COUNTER 
	gosub @GENERATE_RANDOM_COORD
	gosub @CREATE_RANDOM_INJURED_PED
	gosub @GENERATE_TIMELIMIT
	0008: $PED_COUNTER += 1
end
if
	0018:   $NUMBER_OF_INJURED_PEDS > 3 
then
	0004: $BONUS_TIME_FLAG = 1
else
	0004: $BONUS_TIME_FLAG = 0
end
0084: $TIME_CHUNK = $PED_TIME_LIMIT 
0070: $TIME_CHUNK /= $NUMBER_OF_INJURED_PEDS 
0014: $TIME_CHUNK /= 2 
0084: $BRACKETS_VAR = $NUMBER_OF_INJURED_PEDS 
0008: $BRACKETS_VAR += 1 
0068: $TIME_CHUNK *= $BRACKETS_VAR 
0058: $PED_TIME_LIMIT += $TIME_CHUNK 
0070: $PED_TIME_LIMIT /= $NUMBER_OF_INJURED_PEDS 
0084: $TIME_CHUNK = $PED_TIME_LIMIT 
0014: $TIME_CHUNK /= 2 
014E: start_timer_at $PED_TIME_LIMIT 
if
	0038:   $ONMISSION == 0
then
	018A: $HOSPITAL_BLIP = create_checkpoint_at $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z
end

goto @AMBULANCE_LOOP

///////////////////////////

:CREATE_RANDOM_INJURED_PED
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 0
	0038:   $INJURED_PED_1_FLAG == 0
then
	0376: $INJURED_PED_1 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_1_FLAG = 1 
	0332: set_actor $INJURED_PED_1 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_1 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_1 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_1 running 1 
	01ED: clear_actor $INJURED_PED_1 threat_search 
	0187: $INJURED_PED_1_BLIP = create_marker_above_actor $INJURED_PED_1 
	0192: set_actor $INJURED_PED_1 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 1
	0038:   $INJURED_PED_2_FLAG == 0
then
	0376: $INJURED_PED_2 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_2_FLAG = 1 
	0332: set_actor $INJURED_PED_2 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_2 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_2 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_2 running 1 
	01ED: clear_actor $INJURED_PED_2 threat_search 
	0187: $INJURED_PED_2_BLIP = create_marker_above_actor $INJURED_PED_2 
	0192: set_actor $INJURED_PED_2 objective_to_stand_still 
	return 
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 2
	0038:   $INJURED_PED_3_FLAG == 0
then
	0376: $INJURED_PED_3 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_3_FLAG = 1 
	0332: set_actor $INJURED_PED_3 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_3 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_3 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_3 running 1 
	01ED: clear_actor $INJURED_PED_3 threat_search 
	0187: $INJURED_PED_3_BLIP = create_marker_above_actor $INJURED_PED_3 
	0192: set_actor $INJURED_PED_3 objective_to_stand_still 
	return 
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 3
	0038:   $INJURED_PED_4_FLAG == 0
then
	0376: $INJURED_PED_4 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_4_FLAG = 1 
	0332: set_actor $INJURED_PED_4 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_4 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_4 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_4 running 1 
	01ED: clear_actor $INJURED_PED_4 threat_search 
	0187: $INJURED_PED_4_BLIP = create_marker_above_actor $INJURED_PED_4 
	0192: set_actor $INJURED_PED_4 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 4
	0038:   $INJURED_PED_5_FLAG == 0
then
	0376: $INJURED_PED_5 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_5_FLAG = 1 
	0332: set_actor $INJURED_PED_5 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_5 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_5 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_5 running 1 
	01ED: clear_actor $INJURED_PED_5 threat_search 
	0187: $INJURED_PED_5_BLIP = create_marker_above_actor $INJURED_PED_5 
	0192: set_actor $INJURED_PED_5 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 5
	0038:   $INJURED_PED_6_FLAG == 0
then
	0376: $INJURED_PED_6 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_6_FLAG = 1 
	0332: set_actor $INJURED_PED_6 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_6 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_6 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_6 running 1 
	01ED: clear_actor $INJURED_PED_6 threat_search 
	0187: $INJURED_PED_6_BLIP = create_marker_above_actor $INJURED_PED_6 
	0192: set_actor $INJURED_PED_6 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 6
	0038:   $INJURED_PED_7_FLAG == 0
then
	0376: $INJURED_PED_7 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_7_FLAG = 1 
	0332: set_actor $INJURED_PED_7 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_7 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_7 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_7 running 1 
	01ED: clear_actor $INJURED_PED_7 threat_search 
	0187: $INJURED_PED_7_BLIP = create_marker_above_actor $INJURED_PED_7 
	0192: set_actor $INJURED_PED_7 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 7
	0038:   $INJURED_PED_8_FLAG == 0
then
	0376: $INJURED_PED_8 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_8_FLAG = 1 
	0332: set_actor $INJURED_PED_8 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_8 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_8 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_8 running 1 
	01ED: clear_actor $INJURED_PED_8 threat_search 
	0187: $INJURED_PED_8_BLIP = create_marker_above_actor $INJURED_PED_8 
	0192: set_actor $INJURED_PED_8 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 8
	0038:   $INJURED_PED_9_FLAG == 0
then
	0376: $INJURED_PED_9 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_9_FLAG = 1 
	0332: set_actor $INJURED_PED_9 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_9 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_9 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_9 running 1 
	01ED: clear_actor $INJURED_PED_9 threat_search 
	0187: $INJURED_PED_9_BLIP = create_marker_above_actor $INJURED_PED_9 
	0192: set_actor $INJURED_PED_9 objective_to_stand_still
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 9
	0038:   $INJURED_PED_10_FLAG == 0
then
	0376: $INJURED_PED_10 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_10_FLAG = 1 
	0332: set_actor $INJURED_PED_10 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_10 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_10 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_10 running 1 
	01ED: clear_actor $INJURED_PED_10 threat_search 
	0187: $INJURED_PED_10_BLIP = create_marker_above_actor $INJURED_PED_10 
	0192: set_actor $INJURED_PED_10 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 10
	0038:   $INJURED_PED_11_FLAG == 0
then
	0376: $INJURED_PED_11 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_11_FLAG = 1 
	0332: set_actor $INJURED_PED_11 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_11 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_11 z_angle_to $RANDOM_PED_HEADING 
	0319: set_actor $INJURED_PED_11 running 1 
	01ED: clear_actor $INJURED_PED_11 threat_search 
	0187: $INJURED_PED_11_BLIP = create_marker_above_actor $INJURED_PED_11 
	0192: set_actor $INJURED_PED_11 objective_to_stand_still 
	return
end
if and
	0018:   $NUMBER_OF_INJURED_PEDS > 11
	0038:   $INJURED_PED_12_FLAG == 0
then
	0376: $INJURED_PED_12 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
	0004: $INJURED_PED_12_FLAG = 1 
	0332: set_actor $INJURED_PED_12 bleeding_to 1 
	02A9: set_char_only_damaged_by_player $INJURED_PED_12 to 1 
	0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
	0173: set_actor $INJURED_PED_12 z_angle_to $RANDOM_PED_HEADING
	0319: set_actor $INJURED_PED_12 running 1 
	01ED: clear_actor $INJURED_PED_12 threat_search 
	0187: $INJURED_PED_12_BLIP = create_marker_above_actor $INJURED_PED_12 
	0192: set_actor $INJURED_PED_12 objective_to_stand_still 
	return 
end
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	if and
		0018:   $NUMBER_OF_INJURED_PEDS > 12
		0038:   $INJURED_PED_13_FLAG == 0
	then
		0376: $INJURED_PED_13 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
		0004: $INJURED_PED_13_FLAG = 1 
		0332: set_actor $INJURED_PED_13 bleeding_to 1 
		02A9: set_char_only_damaged_by_player $INJURED_PED_13 to 1 
		0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
		0173: set_actor $INJURED_PED_13 z_angle_to $RANDOM_PED_HEADING
		0319: set_actor $INJURED_PED_13 running 1 
		01ED: clear_actor $INJURED_PED_13 threat_search 
		0187: $INJURED_PED_13_BLIP = create_marker_above_actor $INJURED_PED_13 
		0192: set_actor $INJURED_PED_13 objective_to_stand_still 
		return 
	end
	if and
		0018:   $NUMBER_OF_INJURED_PEDS > 13
		0038:   $INJURED_PED_14_FLAG == 0
	then
		0376: $INJURED_PED_14 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
		0004: $INJURED_PED_14_FLAG = 1 
		0332: set_actor $INJURED_PED_14 bleeding_to 1 
		02A9: set_char_only_damaged_by_player $INJURED_PED_14 to 1 
		0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
		0173: set_actor $INJURED_PED_14 z_angle_to $RANDOM_PED_HEADING
		0319: set_actor $INJURED_PED_14 running 1 
		01ED: clear_actor $INJURED_PED_14 threat_search 
		0187: $INJURED_PED_14_BLIP = create_marker_above_actor $INJURED_PED_14 
		0192: set_actor $INJURED_PED_14 objective_to_stand_still 
		return 
	end
	if and
		0018:   $NUMBER_OF_INJURED_PEDS > 14
		0038:   $INJURED_PED_15_FLAG == 0
	then
		0376: $INJURED_PED_15 = create_random_actor $PED_COORD_X $PED_COORD_Y $PED_COORD_Z 
		0004: $INJURED_PED_15_FLAG = 1 
		0332: set_actor $INJURED_PED_15 bleeding_to 1 
		02A9: set_char_only_damaged_by_player $INJURED_PED_15 to 1 
		0208: $RANDOM_PED_HEADING = random_float 0.0 359.875 
		0173: set_actor $INJURED_PED_15 z_angle_to $RANDOM_PED_HEADING
		0319: set_actor $INJURED_PED_15 running 1 
		01ED: clear_actor $INJURED_PED_15 threat_search 
		0187: $INJURED_PED_15_BLIP = create_marker_above_actor $INJURED_PED_15 
		0192: set_actor $INJURED_PED_15 objective_to_stand_still 
		return 
	end
end

return

///////////////////////////

:GENERATE_RANDOM_COORD
wait 0 ms
0054: get_player_coordinates $PLAYER_CHAR store_to $PLAYER1_A_X $PLAYER1_A_Y $PLAYER1_A_Z 

if
	03C6:   current_island == 1 
then
	0208: $RANDOM_X = random_float 778.0 1540.0 
	0208: $RANDOM_Y = random_float -1110.0 0.0 
	0005: $HOSPITAL_X = 1141.5 
	0005: $HOSPITAL_Y = -595.1875 
	0005: $HOSPITAL_Z = 13.875 
	0005: $HOSPITAL_DOOR_X = 1149.125 
	0005: $HOSPITAL_DOOR_Y = -597.3125 
	0004: $FLAG_GOT_RANGE_MESSAGE = 0 
	0004: $PLAYER_IN_RANGE_FLAG = 1 
	0004: $PARAMEDIC_LOCATION = 1 
end

if
	03C6:   current_island == 2
then
	0208: $RANDOM_X = random_float -192.0 545.0 
	0208: $RANDOM_Y = random_float -1626.0 98.0 
	0005: $HOSPITAL_X = 178.5 
	0005: $HOSPITAL_Y = -23.5625 
	0005: $HOSPITAL_Z = 15.1875 
	0005: $HOSPITAL_DOOR_X = 182.75 
	0005: $HOSPITAL_DOOR_Y = -15.75 
	0004: $FLAG_GOT_RANGE_MESSAGE = 0 
	0004: $PLAYER_IN_RANGE_FLAG = 1 
	0004: $PARAMEDIC_LOCATION = 2 
end

if
	03C6:   current_island == 3
then
	0208: $RANDOM_X = random_float -1300.0 -414.0 
	0208: $RANDOM_Y = random_float -608.75 380.0 
	0005: $HOSPITAL_X = -1255.5 
	0005: $HOSPITAL_Y = -144.375 
	0005: $HOSPITAL_Z = 57.75 
	0005: $HOSPITAL_DOOR_X = -1246.75 
	0005: $HOSPITAL_DOOR_Y = -139.0625 
	0004: $FLAG_GOT_RANGE_MESSAGE = 0 
	0004: $PLAYER_IN_RANGE_FLAG = 1 
	0004: $PARAMEDIC_LOCATION = 3 
end

if and
	0038:   $PLAYER_IN_RANGE_FLAG == 0 
	0038:   $FLAG_GOT_RANGE_MESSAGE == 0
then
	if
		0038:   $FLAG_GOT_RANGE_MESSAGE == 0
	then
		00BC: print_now 'A_RANGE' time 5000 flag 1  // ~g~The ambulance radio is out of range, get closer to a hospital!
		0004: $FLAG_GOT_RANGE_MESSAGE = 1
	end
	goto @AMBULANCE_FAILED
end

gosub @AMBULANCE_CHECK_FAIL_CRITERIA

02C0: set $PED_COORD_X $PED_COORD_Y $PED_COORD_Z to_ped_path_coords_closest_to $RANDOM_X $RANDOM_Y $PLAYER1_A_Z

if and // COLOMBIAN BOAT
	0020:   $PED_COORD_X > 1398.0 
	0022:   1615.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -965.0 
	0022:   -902.0 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // BACK OF LUIGI'S
	0020:   $PED_COORD_X > 879.0 
	0022:   892.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -427.0 
	0022:   -407.0 > $PED_COORD_Y  
then
	goto @GENERATE_RANDOM_COORD
end
if and // FISH FACTORY
	0020:   $PED_COORD_X > 944.75 
	0022:   1017.063 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -1148.75 
	0022:   -1076.563 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // CHINATOWN MARKET
	0020:   $PED_COORD_X > 920.75 
	0022:   1004.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -754.1875 
	0022:   -670.0 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // CALAHAN BRIDGE
	0020:   $PED_COORD_X > 670.0 
	0022:   1035.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -953.0 
	0022:   -912.0 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // DOCKS INDUSTRIAL
	0020:   $PED_COORD_X > 1364.0 
	0022:   1641.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -1165.0 
	0022:   -617.0 > $PED_COORD_Y
then
	goto @GENERATE_RANDOM_COORD
end
if and // TUNNEL ENTRANCE INDUSTRIAL
	0020:   $PED_COORD_X > 649.0 
	0022:   1066.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > 25.0 
	0022:   217.0 > $PED_COORD_Y
then
	goto @GENERATE_RANDOM_COORD
end
if and // AIRPORT SUBURBAN
	0020:   $PED_COORD_X > -1611.5 
	0022:   -745.25 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -1001.875 
	0022:   -371.1875 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // OLD SCHOOL HALL AND PARK AREA
	0020:   $PED_COORD_X > 939.75 
	0022:   1035.563 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -901.25 
	0022:   -828.1875 > $PED_COORD_Y
then
	goto @GENERATE_RANDOM_COORD
end
if and // DOG FOOD FACTORY
	0020:   $PED_COORD_X > 1215.25 
	0022:   1223.688 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -839.375 
	0022:   -763.5625 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // INDUSTRIAL SAFEHOUSE
	0020:   $PED_COORD_X > 845.25 
	0022:   899.5625 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -312.5625 
	0022:   -295.6875 > $PED_COORD_Y
then
	goto @GENERATE_RANDOM_COORD
end
if and // DOJO COMMERCIAL
	0020:   $PED_COORD_X > 113.25 
	0022:   99.6875 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -1284.75 
	0022:   -1273.0 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // COLOMBIAN OJG COMPOUND
	0020:   $PED_COORD_X > 18.3125 
	0022:   92.0 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -388.6875 
	0022:   -312.375 > $PED_COORD_Y
then
	goto @GENERATE_RANDOM_COORD
end
if and // BAIT WAREHOUSE CARPARK SUBURBIA
	0020:   $PED_COORD_X > -1255.375 
	0022:   -1187.875 > $PED_COORD_X 
	0020:   $PED_COORD_Y > 80.5625 
	0022:   123.375 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end
if and // FRANKIES HOUSE
	0020:   $PED_COORD_X > 1386.375 
	0022:   1475.75 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -292.0625 
	0022:   -168.0 > $PED_COORD_Y
then
	goto @GENERATE_RANDOM_COORD
end
if and // FOOT BRIDGE CHINATOWN
	0020:   $PED_COORD_X > 766.6875 
	0022:   851.75 > $PED_COORD_X 
	0020:   $PED_COORD_Y > -604.0625 
	0022:   -558.1875 > $PED_COORD_Y 
then
	goto @GENERATE_RANDOM_COORD
end

if
	0038:   $PARAMEDIC_LOCATION == 1 
then
	if or // INDUSTRIAL
		8020:   not  $PED_COORD_X > 778.0 
		8022:   not  1540.0 > $PED_COORD_X 
		8020:   not  $PED_COORD_Y > -1110.0 
		8022:   not  190.0 > $PED_COORD_Y 
	then
		goto @GENERATE_RANDOM_COORD
	end
end

if
	0038:   $PARAMEDIC_LOCATION == 2
then
	if or // COMMERCIAL
		8020:   not  $PED_COORD_X > 778.0 
		8022:   not  1540.0 > $PED_COORD_X 
		8020:   not  $PED_COORD_Y > -1110.0 
		8022:   not  190.0 > $PED_COORD_Y 
	then
		goto @GENERATE_RANDOM_COORD
	end
end

if
	0038:   $PARAMEDIC_LOCATION == 3
then
	if or // SUBURBIA
		8020:   not  $PED_COORD_X > 778.0 
		8022:   not  1540.0 > $PED_COORD_X 
		8020:   not  $PED_COORD_Y > -1110.0 
		8022:   not  190.0 > $PED_COORD_Y 
	then
		goto @GENERATE_RANDOM_COORD
	end
end

if
	0022:   -1.0 > $PED_COORD_Z 
then
	goto @GENERATE_RANDOM_COORD
end

0086: $DIFFERENCE_X_FLOAT_A = $PLAYER1_A_X 
0061: $DIFFERENCE_X_FLOAT_A -= $PED_COORD_X 
0086: $DIFFERENCE_Y_FLOAT_A = $PLAYER1_A_Y 
0061: $DIFFERENCE_Y_FLOAT_A -= $PED_COORD_Y 
0069: $DIFFERENCE_X_FLOAT_A *= $DIFFERENCE_X_FLOAT_A 
0069: $DIFFERENCE_Y_FLOAT_A *= $DIFFERENCE_Y_FLOAT_A 
0086: $SUM_DIFFERENCE_A_XY = $DIFFERENCE_X_FLOAT_A 
0059: $SUM_DIFFERENCE_A_XY += $DIFFERENCE_Y_FLOAT_A 
01FB: $PLAYERS_DISTANCE_FROM_PED = square_root $SUM_DIFFERENCE_A_XY
if
	0022:   120.0 > $PLAYERS_DISTANCE_FROM_PED 
then
	goto @GENERATE_RANDOM_COORD
end

0086: $DIFFERENCE_X_FLOAT_A = $HOSPITAL_X 
0061: $DIFFERENCE_X_FLOAT_A -= $PED_COORD_X 
0086: $DIFFERENCE_Y_FLOAT_A = $HOSPITAL_Y 
0061: $DIFFERENCE_Y_FLOAT_A -= $PED_COORD_Y 
0069: $DIFFERENCE_X_FLOAT_A *= $DIFFERENCE_X_FLOAT_A 
0069: $DIFFERENCE_Y_FLOAT_A *= $DIFFERENCE_Y_FLOAT_A 
0086: $SUM_DIFFERENCE_A_XY = $DIFFERENCE_X_FLOAT_A 
0059: $SUM_DIFFERENCE_A_XY += $DIFFERENCE_Y_FLOAT_A 
01FB: $PEDS_DISTANCE_FROM_HOSPITAL = square_root $SUM_DIFFERENCE_A_XY 
if
	0022:   100.0 > $PEDS_DISTANCE_FROM_HOSPITAL
then
	goto @GENERATE_RANDOM_COORD
end

if
	0018:   $INJURED_PED_1_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_1 dead
	then
		if
			00EC:   actor $INJURED_PED_1 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_2_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_2 dead
	then
		if
			00EC:   actor $INJURED_PED_2 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_3_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_3 dead
	then
		if
			00EC:   actor $INJURED_PED_3 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_4_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_4 dead
	then
		if
			00EC:   actor $INJURED_PED_4 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_5_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_5 dead
	then
		if
			00EC:   actor $INJURED_PED_5 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_6_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_6 dead
	then
		if
			00EC:   actor $INJURED_PED_6 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_7_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_7 dead
	then
		if
			00EC:   actor $INJURED_PED_7 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_8_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_8 dead
	then
		if
			00EC:   actor $INJURED_PED_8 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_9_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_9 dead
	then
		if
			00EC:   actor $INJURED_PED_9 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_10_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_10 dead
	then
		if
			00EC:   actor $INJURED_PED_10 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0018:   $INJURED_PED_11_FLAG > 0
then
	if
		8118:   not actor $INJURED_PED_11 dead
	then
		if
			00EC:   actor $INJURED_PED_11 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
		then
			goto @GENERATE_RANDOM_COORD
		end
	end
end

if
	0038:   $UNLOCKEXTRAS1 == 1
then
	if
		0018:   $INJURED_PED_12_FLAG > 0
	then
		if
			8118:   not actor $INJURED_PED_12 dead
		then
			if
				00EC:   actor $INJURED_PED_12 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
			then
				goto @GENERATE_RANDOM_COORD
			end
		end
	end
	if
		0018:   $INJURED_PED_13_FLAG > 0
	then
		if
			8118:   not actor $INJURED_PED_13 dead
		then
			if
				00EC:   actor $INJURED_PED_13 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
			then
				goto @GENERATE_RANDOM_COORD
			end
		end
	end
	if
		0018:   $INJURED_PED_14_FLAG > 0
	then
		if
			8118:   not actor $INJURED_PED_14 dead
		then
			if
				00EC:   actor $INJURED_PED_14 0 $PED_COORD_X $PED_COORD_Y radius 25.0 25.0 
			then
				goto @GENERATE_RANDOM_COORD
			end
		end
	end
end

return

///////////////////////////

:GENERATE_TIMELIMIT
if
	0038:   $PARAMEDIC_LOCATION == 3 
then
	0005: $TIME_CHUNK_DIVIDER = 10.0
end

0086: $PED_TIME_LIMIT_FLOAT = $PEDS_DISTANCE_FROM_HOSPITAL 
0071: $PED_TIME_LIMIT_FLOAT /= $TIME_CHUNK_DIVIDER 
0011: $PED_TIME_LIMIT_FLOAT *= 1000.0 
008C: $PED_TIME_LIMIT_TEMP = float_to_integer $PED_TIME_LIMIT_FLOAT 
0058: $PED_TIME_LIMIT += $PED_TIME_LIMIT_TEMP 
0051: return 

///////////////////////////

:AMBULANCE_LOOP

wait 0 ms

gosub @AMBULANCE_CHECK_FAIL_CRITERIA

00DA: store_car_player_is_in $PLAYER_CHAR store_to $PLAYERS_AMBULANCE

if and
	0018:   $NUMBER_OF_INJURED_PEDS > 6 
	0038:   $BONUS_TIME_FLAG == 2 
	0038:   $DROP_OFF_TIME_BONUS == 0 
then
	0004: $BONUS_TIME_FLAG = 1 
	0008: $DROP_OFF_TIME_BONUS += 1 
end

if and
	0018:   $NUMBER_OF_INJURED_PEDS > 9 
	0038:   $BONUS_TIME_FLAG == 2 
	0038:   $DROP_OFF_TIME_BONUS == 1 
then
	0004: $BONUS_TIME_FLAG = 1 
	0008: $DROP_OFF_TIME_BONUS += 1
end

if and
	0018:   $NUMBER_OF_INJURED_PEDS > 12 
	0038:   $BONUS_TIME_FLAG == 2 
	0038:   $DROP_OFF_TIME_BONUS == 2
then
	0004: $BONUS_TIME_FLAG = 1 
	0008: $DROP_OFF_TIME_BONUS += 1
end

gosub @AMBULANCE_CHECK_REWARDS


///////////////////////////

if
	0018:   $INJURED_PED_1_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_1 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_1 
		034F: destroy_actor_with_fade $INJURED_PED_1 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_1_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_1
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_1 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_1_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_1 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_1 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_1_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_1_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_1 radius 25.0 25.0 
		then
			0004: $INJURED_PED_1_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_1_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_1
		then
			0164: disable_marker $INJURED_PED_1_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_1_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_1_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_1 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_1_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_1_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_1
		then
			0211: actor $INJURED_PED_1 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_1 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_1_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_2_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_2 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_2 
		034F: destroy_actor_with_fade $INJURED_PED_2 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_2_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_2
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_2 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_2_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_2 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_2 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_2_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_2_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_2 radius 25.0 25.0 
		then
			0004: $INJURED_PED_2_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_2_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_2
		then
			0164: disable_marker $INJURED_PED_2_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_2_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_2_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_2 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_2_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_2_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_2
		then
			0211: actor $INJURED_PED_2 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_2 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_2_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_3_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_3 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_3 
		034F: destroy_actor_with_fade $INJURED_PED_3 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_3_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_3
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_3 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_3_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_3 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_3 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_3_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_3_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_3 radius 25.0 25.0 
		then
			0004: $INJURED_PED_3_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_3_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_3
		then
			0164: disable_marker $INJURED_PED_3_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_3_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_3_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_3 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_3_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_3_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_3
		then
			0211: actor $INJURED_PED_3 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_3 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_3_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_4_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_4 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_4 
		034F: destroy_actor_with_fade $INJURED_PED_4 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_4_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_4
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_4 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_4_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_4 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_4 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_4_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_4_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_4 radius 25.0 25.0 
		then
			0004: $INJURED_PED_4_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_4_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_4
		then
			0164: disable_marker $INJURED_PED_4_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_4_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_4_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_4 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_4_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_4_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_4
		then
			0211: actor $INJURED_PED_4 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_4 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_4_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_5_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_5 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_5 
		034F: destroy_actor_with_fade $INJURED_PED_5 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_5_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_5
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_5 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_5_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_5 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_5 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_5_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_5_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_5 radius 25.0 25.0 
		then
			0004: $INJURED_PED_5_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_5_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_5
		then
			0164: disable_marker $INJURED_PED_5_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_5_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_5_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_5 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_5_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_5_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_5
		then
			0211: actor $INJURED_PED_5 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_5 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_5_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_6_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_6 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_6 
		034F: destroy_actor_with_fade $INJURED_PED_6 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_6_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_6
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_6 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_6_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_6 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_6 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_6_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_6_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_6 radius 25.0 25.0 
		then
			0004: $INJURED_PED_6_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_6_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_6
		then
			0164: disable_marker $INJURED_PED_6_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_6_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_6_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_6 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_6_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_6_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_6
		then
			0211: actor $INJURED_PED_6 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_6 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_6_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_7_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_7 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_7 
		034F: destroy_actor_with_fade $INJURED_PED_7 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_7_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_7
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_7 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_7_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_7 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_7 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_7_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_7_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_7 radius 25.0 25.0 
		then
			0004: $INJURED_PED_7_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_7_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_7
		then
			0164: disable_marker $INJURED_PED_7_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_7_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_7_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_7 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_7_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_7_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_7
		then
			0211: actor $INJURED_PED_7 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_7 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_7_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_8_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_8 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_8 
		034F: destroy_actor_with_fade $INJURED_PED_8 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_8_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_8
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_8 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_8_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_8 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_8 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_8_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_8_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_8 radius 25.0 25.0 
		then
			0004: $INJURED_PED_8_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_8_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_8
		then
			0164: disable_marker $INJURED_PED_8_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_8_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_8_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_8 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_8_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_8_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_8
		then
			0211: actor $INJURED_PED_8 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_8 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_8_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_9_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_9 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_9 
		034F: destroy_actor_with_fade $INJURED_PED_9 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_9_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_9
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_9 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_9_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_9 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_9 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_9_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_9_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_9 radius 25.0 25.0 
		then
			0004: $INJURED_PED_9_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_9_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_9
		then
			0164: disable_marker $INJURED_PED_9_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_9_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_9_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_9 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_9_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_9_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_9
		then
			0211: actor $INJURED_PED_9 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_9 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_9_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_10_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_10 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_10 
		034F: destroy_actor_with_fade $INJURED_PED_10 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_10_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_10
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_10 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_10_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_10 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_10 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_10_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_10_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_10 radius 25.0 25.0 
		then
			0004: $INJURED_PED_10_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_10_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_10
		then
			0164: disable_marker $INJURED_PED_10_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_10_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_10_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_10 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_10_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_10_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_10
		then
			0211: actor $INJURED_PED_10 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_10 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_10_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_11_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_11 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_11 
		034F: destroy_actor_with_fade $INJURED_PED_11 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_11_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_11
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_11 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_11_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_11 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_11 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_11_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_11_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_11 radius 25.0 25.0 
		then
			0004: $INJURED_PED_11_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_11_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_11
		then
			0164: disable_marker $INJURED_PED_11_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_11_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_11_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_11 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_11_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_11_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_11
		then
			0211: actor $INJURED_PED_11 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_11 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_11_FLAG = 0 
		end
	end
end

///////////////////////////

if
	0018:   $INJURED_PED_12_FLAG > 0
then
	if
		0118:   actor $INJURED_PED_12 dead
	then
		00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $PED_TIME_LIMIT == 0 
	then
		0321: kill_actor $INJURED_PED_12 
		034F: destroy_actor_with_fade $INJURED_PED_12 
		00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
		goto @AMBULANCE_FAILED
	end
	if
		0038:   $INJURED_PED_12_FLAG == 3 
	then
		if
			03A3:   is_char_male $INJURED_PED_12
		then
			0004: $PED_SEX_FLAG = 0 
		else
			0004: $PED_SEX_FLAG = 1
		end
		00A0: get_char_coordinates $INJURED_PED_12 store_to $SOUND_X $SOUND_Y $SOUND_Z 
		gosub @CHUNK1_AMBULANCE
	end
	if
		0038:   $INJURED_PED_12_FLAG == 1
	then
		if
			00FD:   player $PLAYER_CHAR 0 $INJURED_PED_12 in_car radius 10.0 10.0 2.0 
		then
			gosub @CHUNK2_AMBULANCE
			if
				0038:   $CAR_FULL_FLAG == 0
			then
				01D4: actor $INJURED_PED_12 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
				0004: $INJURED_PED_12_FLAG = 2
			end
		end
	end
	if
		0038:   $INJURED_PED_12_FLAG == 2
	then
		if
			80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_12 radius 25.0 25.0 
		then
			0004: $INJURED_PED_12_FLAG = 1
		end
	end
	if
		0038:   $INJURED_PED_12_FLAG == 2
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_12
		then
			0164: disable_marker $INJURED_PED_12_BLIP 
			gosub @CHUNK3_AMBULANCE
			0004: $INJURED_PED_12_FLAG = 3
		end
	end
	if
		0038:   $INJURED_PED_12_FLAG == 3
	then
		if
			00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
		then
			01D3: actor $INJURED_PED_12 leave_car $PLAYERS_AMBULANCE 
			0004: $INJURED_PED_12_FLAG = 4 
		end
	end
	if
		0038:   $INJURED_PED_12_FLAG == 4
	then
		if
			00DF:   is_char_in_any_car $INJURED_PED_12
		then
			0211: actor $INJURED_PED_12 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
			01C2: remove_references_to_actor $INJURED_PED_12 
			0050: gosub @CHUNK4_AMBULANCE 
			0004: $INJURED_PED_12_FLAG = 0 
		end
	end
end

///////////////////////////
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	if
		0018:   $INJURED_PED_13_FLAG > 0
	then
		if
			0118:   actor $INJURED_PED_13 dead
		then
			00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
			goto @AMBULANCE_FAILED
		end
		if
			0038:   $PED_TIME_LIMIT == 0 
		then
			0321: kill_actor $INJURED_PED_13 
			034F: destroy_actor_with_fade $INJURED_PED_13 
			00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
			goto @AMBULANCE_FAILED
		end
		if
			0038:   $INJURED_PED_13_FLAG == 3 
		then
			if
				03A3:   is_char_male $INJURED_PED_13
			then
				0004: $PED_SEX_FLAG = 0 
			else
				0004: $PED_SEX_FLAG = 1
			end
			00A0: get_char_coordinates $INJURED_PED_13 store_to $SOUND_X $SOUND_Y $SOUND_Z 
			gosub @CHUNK1_AMBULANCE
		end
		if
			0038:   $INJURED_PED_13_FLAG == 1
		then
			if
				00FD:   player $PLAYER_CHAR 0 $INJURED_PED_13 in_car radius 10.0 10.0 2.0 
			then
				gosub @CHUNK2_AMBULANCE
				if
					0038:   $CAR_FULL_FLAG == 0
				then
					01D4: actor $INJURED_PED_13 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
					0004: $INJURED_PED_13_FLAG = 2
				end
			end
		end
		if
			0038:   $INJURED_PED_13_FLAG == 2
		then
			if
				80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_13 radius 25.0 25.0 
			then
				0004: $INJURED_PED_13_FLAG = 1
			end
		end
		if
			0038:   $INJURED_PED_13_FLAG == 2
		then
			if
				00DF:   is_char_in_any_car $INJURED_PED_13
			then
				0164: disable_marker $INJURED_PED_13_BLIP 
				gosub @CHUNK3_AMBULANCE
				0004: $INJURED_PED_13_FLAG = 3
			end
		end
		if
			0038:   $INJURED_PED_13_FLAG == 3
		then
			if
				00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
			then
				01D3: actor $INJURED_PED_13 leave_car $PLAYERS_AMBULANCE 
				0004: $INJURED_PED_13_FLAG = 4 
			end
		end
		if
			0038:   $INJURED_PED_13_FLAG == 4
		then
			if
				00DF:   is_char_in_any_car $INJURED_PED_13
			then
				0211: actor $INJURED_PED_13 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
				01C2: remove_references_to_actor $INJURED_PED_13 
				0050: gosub @CHUNK4_AMBULANCE 
				0004: $INJURED_PED_13_FLAG = 0 
			end
		end
	end

///////////////////////////

	if
		0018:   $INJURED_PED_14_FLAG > 0
	then
		if
			0118:   actor $INJURED_PED_14 dead
		then
			00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
			goto @AMBULANCE_FAILED
		end
		if
			0038:   $PED_TIME_LIMIT == 0 
		then
			0321: kill_actor $INJURED_PED_14 
			034F: destroy_actor_with_fade $INJURED_PED_14 
			00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
			goto @AMBULANCE_FAILED
		end
		if
			0038:   $INJURED_PED_14_FLAG == 3 
		then
			if
				03A3:   is_char_male $INJURED_PED_14
			then
				0004: $PED_SEX_FLAG = 0 
			else
				0004: $PED_SEX_FLAG = 1
			end
			00A0: get_char_coordinates $INJURED_PED_14 store_to $SOUND_X $SOUND_Y $SOUND_Z 
			gosub @CHUNK1_AMBULANCE
		end
		if
			0038:   $INJURED_PED_14_FLAG == 1
		then
			if
				00FD:   player $PLAYER_CHAR 0 $INJURED_PED_14 in_car radius 10.0 10.0 2.0 
			then
				gosub @CHUNK2_AMBULANCE
				if
					0038:   $CAR_FULL_FLAG == 0
				then
					01D4: actor $INJURED_PED_14 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
					0004: $INJURED_PED_14_FLAG = 2
				end
			end
		end
		if
			0038:   $INJURED_PED_14_FLAG == 2
		then
			if
				80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_14 radius 25.0 25.0 
			then
				0004: $INJURED_PED_14_FLAG = 1
			end
		end
		if
			0038:   $INJURED_PED_14_FLAG == 2
		then
			if
				00DF:   is_char_in_any_car $INJURED_PED_14
			then
				0164: disable_marker $INJURED_PED_14_BLIP 
				gosub @CHUNK3_AMBULANCE
				0004: $INJURED_PED_14_FLAG = 3
			end
		end
		if
			0038:   $INJURED_PED_14_FLAG == 3
		then
			if
				00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
			then
				01D3: actor $INJURED_PED_14 leave_car $PLAYERS_AMBULANCE 
				0004: $INJURED_PED_14_FLAG = 4 
			end
		end
		if
			0038:   $INJURED_PED_14_FLAG == 4
		then
			if
				00DF:   is_char_in_any_car $INJURED_PED_14
			then
				0211: actor $INJURED_PED_14 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
				01C2: remove_references_to_actor $INJURED_PED_14 
				0050: gosub @CHUNK4_AMBULANCE 
				0004: $INJURED_PED_14_FLAG = 0 
			end
		end
	end

///////////////////////////


	if
		0018:   $INJURED_PED_15_FLAG > 0
	then
		if
			0118:   actor $INJURED_PED_15 dead
		then
			00BC: print_now 'A_FAIL3' time 3000 flag 1  // ~r~The patient is dead!!
			goto @AMBULANCE_FAILED
		end
		if
			0038:   $PED_TIME_LIMIT == 0 
		then
			0321: kill_actor $INJURED_PED_15 
			034F: destroy_actor_with_fade $INJURED_PED_15 
			00BC: print_now 'A_FAIL2' time 3000 flag 1  // ~r~Your lack of urgency has been fatal to the patient!
			goto @AMBULANCE_FAILED
		end
		if
			0038:   $INJURED_PED_15_FLAG == 3 
		then
			if
				03A3:   is_char_male $INJURED_PED_15
			then
				0004: $PED_SEX_FLAG = 0 
			else
				0004: $PED_SEX_FLAG = 1
			end
			00A0: get_char_coordinates $INJURED_PED_15 store_to $SOUND_X $SOUND_Y $SOUND_Z 
			gosub @CHUNK1_AMBULANCE
		end
		if
			0038:   $INJURED_PED_15_FLAG == 1
		then
			if
				00FD:   player $PLAYER_CHAR 0 $INJURED_PED_15 in_car radius 10.0 10.0 2.0 
			then
				gosub @CHUNK2_AMBULANCE
				if
					0038:   $CAR_FULL_FLAG == 0
				then
					01D4: actor $INJURED_PED_15 go_to_car $PLAYERS_AMBULANCE and_enter_it_as_a_passenger 
					0004: $INJURED_PED_15_FLAG = 2
				end
			end
		end
		if
			0038:   $INJURED_PED_15_FLAG == 2
		then
			if
				80EB:   not player $PLAYER_CHAR 0 $INJURED_PED_15 radius 25.0 25.0 
			then
				0004: $INJURED_PED_15_FLAG = 1
			end
		end
		if
			0038:   $INJURED_PED_15_FLAG == 2
		then
			if
				00DF:   is_char_in_any_car $INJURED_PED_15
			then
				0164: disable_marker $INJURED_PED_15_BLIP 
				gosub @CHUNK3_AMBULANCE
				0004: $INJURED_PED_15_FLAG = 3
			end
		end
		if
			0038:   $INJURED_PED_15_FLAG == 3
		then
			if
				00FA:   player $PLAYER_CHAR stopped 1 $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z radius 6.0 6.0 2.0
			then
				01D3: actor $INJURED_PED_15 leave_car $PLAYERS_AMBULANCE 
				0004: $INJURED_PED_15_FLAG = 4 
			end
		end
		if
			0038:   $INJURED_PED_15_FLAG == 4
		then
			if
				00DF:   is_char_in_any_car $INJURED_PED_15
			then
				0211: actor $INJURED_PED_15 walk_to $HOSPITAL_DOOR_X $HOSPITAL_DOOR_Y 
				01C2: remove_references_to_actor $INJURED_PED_15 
				0050: gosub @CHUNK4_AMBULANCE 
				0004: $INJURED_PED_15_FLAG = 0 
			end
		end
	end
end

///////////////////////////

if
	003A:   $SAVED_PEDS == $NUMBER_OF_INJURED_PEDS 
then
	0084: $SCORE_AM = $SAVED_PEDS 
	0068: $SCORE_AM *= $AMBULANCE_LEVEL 
	0010: $SCORE_AM *= 100 
	01E3: text_1number_styled 'REWARD' number $SCORE_AM time 6000 style 6  // REWARD $~1~
	0058: $TOTAL_SAVED_PEDS += $SAVED_PEDS 
	gosub @AMBULANCE_CHECK_REWARDS
	0058: $SAVED_PEDS_THIS_GO += $SAVED_PEDS 
	0008: $NUMBER_OF_INJURED_PEDS += 1 
	0004: $SAVED_PEDS = 0 
	0004: $PED_COUNTER = 0 
	0109: player $PLAYER_CHAR money += $SCORE_AM 
	0004: $HOSPITAL_BLIP_FLAG = 0 
	0164: disable_marker $HOSPITAL_BLIP 
	0164: disable_marker $INJURED_PED_1_BLIP 
	0164: disable_marker $INJURED_PED_2_BLIP 
	0164: disable_marker $INJURED_PED_3_BLIP 
	0164: disable_marker $INJURED_PED_4_BLIP 
	0164: disable_marker $INJURED_PED_5_BLIP 
	0164: disable_marker $INJURED_PED_6_BLIP 
	0164: disable_marker $INJURED_PED_7_BLIP 
	0164: disable_marker $INJURED_PED_8_BLIP 
	0164: disable_marker $INJURED_PED_9_BLIP 
	0164: disable_marker $INJURED_PED_10_BLIP 
	0164: disable_marker $INJURED_PED_11_BLIP 
	0164: disable_marker $INJURED_PED_12_BLIP 
	if
		0038: $UNLOCKEXTRAS1 == 1
	then
		0164: disable_marker $INJURED_PED_13_BLIP 
		0164: disable_marker $INJURED_PED_14_BLIP 
		0164: disable_marker $INJURED_PED_15_BLIP 
	end
	0403: save_highest_ambulance_level $AMBULANCE_LEVEL 
	0008: $AMBULANCE_LEVEL += 1 
	if
		0038: $UNLOCKEXTRAS1 == 1
	then
		if
			0038:   $AMBULANCE_LEVEL == 16
		then
			00BA: print_big 'A_COMP1' time 5000 style 5  // Paramedic missions complete!
			00BA: print_big 'A_COMP2' time 6000 style 6  // You will never get tired!
			014D: text_pager 'A_COMP3' 140 100 1  // Paramedic missions complete! You will never get tired when running!
			0394: play_music 1 
			0330: set_player $PLAYER_CHAR infinite_run_to 1 
			030C: progress_made = 1 
			return 
		end
	else
		if
			0038:   $AMBULANCE_LEVEL == 13
		then
			00BA: print_big 'A_COMP1' time 5000 style 5  // Paramedic missions complete!
			00BA: print_big 'A_COMP2' time 6000 style 6  // You will never get tired!
			014D: text_pager 'A_COMP3' 140 100 1  // Paramedic missions complete! You will never get tired when running!
			0394: play_music 1 
			0330: set_player $PLAYER_CHAR infinite_run_to 1 
			030C: progress_made = 1 
			return 
		end
	end
	goto @MISSION_ROOT
end
goto @AMBULANCE_LOOP

//////////////////////////////////////////////////////////////////////
:AMBULANCE_CHECK_REWARDS
if
	0038:   $AMBULANCE_PAGER_FLAG == 0 
then
	if
		0018:   $TOTAL_SAVED_PEDS > 34 
	then
		014D: text_pager 'PAGEB13' 140 100 1  // Health delivered to hideout
		gosub @PROGRESS_COUNTER1
		0004: $AMBULANCE_PAGER_FLAG = 1
	end
end

if
	0038:   $AMBULANCE_PAGER_FLAG == 1
then
	if
		0018:   $TOTAL_SAVED_PEDS > 69
	then
		014D: text_pager 'PAGEB14' 140 100 1  // Adrenaline delivered to hideout
		gosub @PROGRESS_COUNTER2
		0004: $AMBULANCE_PAGER_FLAG = 2
	end
end
if and
	0038:   $AMBULANCE_PAGER_FLAG == 2
	0038:   $UNLOCKEXTRAS1 == 1
then
	if
		0018:   $TOTAL_SAVED_PEDS > 104
	then
		//014D: text_pager 'PAGEB14' 140 100 1  // Adrenaline delivered to hideout
		//gosub @PROGRESS_COUNTER2
		0004: $AMBULANCE_PAGER_FLAG = 3
	end
end
return

//////////////////////////////////////////////////////////////////////
:AMBULANCE_CHECK_FAIL_CRITERIA
0293: $CONTROLMODE = get_controller_mode 
if
	8038:   not  $CONTROLMODE == 3 
then
	if
		00E1:   is_button_pressed 0 button 19
	then
		0004: $MISSION_END_BUTTON_AMBULANCE = 1 
	end
else
	if
		00E1:   is_button_pressed 0 button 14
	then
		0004: $MISSION_END_BUTTON_AMBULANCE = 1 
	end
end

if
	0038:   $MISSION_END_BUTTON_AMBULANCE == 1
then
	if
		8038:   not  $CONTROLMODE == 3 
	then
		if
			80E1:   is_button_pressed 0 button 19
		then
			00BC: print_now 'A_CANC' time 3000 flag 1  // ~r~Paramedic mission cancelled!
			goto @AMBULANCE_FAILED
		end
	else
		if
			80E1:   is_button_pressed 0 button 14
		then
			00BC: print_now 'A_CANC' time 3000 flag 1  // ~r~Paramedic mission cancelled!
			goto @AMBULANCE_FAILED
		end
	end
end

if
	80DE:   not is_player_in_model $PLAYER_CHAR model #AMBULAN
then
	00BC: print_now 'A_CANC' time 3000 flag 1  // ~r~Paramedic mission cancelled!
	goto @AMBULANCE_FAILED
end
return

//////////////////////////////////////////////////////////////////////
:AMBULANCE_FAILED
014F: stop_timer $PED_TIME_LIMIT 
03E6: remove_text_box 
00BA: print_big 'A_FAIL1' time 5000 style 5  // Paramedic mission ended.
01E3: text_1number_styled 'A_SAVES' number $SAVED_PEDS_THIS_GO time 6000 style 6  // PEOPLE SAVED: ~1~
0004: $HOSPITAL_BLIP_FLAG = 0 
0164: disable_marker $HOSPITAL_BLIP 
0164: disable_marker $INJURED_PED_1_BLIP 
0164: disable_marker $INJURED_PED_2_BLIP 
0164: disable_marker $INJURED_PED_3_BLIP 
0164: disable_marker $INJURED_PED_4_BLIP 
0164: disable_marker $INJURED_PED_5_BLIP 
0164: disable_marker $INJURED_PED_6_BLIP 
0164: disable_marker $INJURED_PED_7_BLIP 
0164: disable_marker $INJURED_PED_8_BLIP 
0164: disable_marker $INJURED_PED_9_BLIP 
0164: disable_marker $INJURED_PED_10_BLIP 
0164: disable_marker $INJURED_PED_11_BLIP 
0164: disable_marker $INJURED_PED_12_BLIP 
if
	0038: $UNLOCKEXTRAS1 == 1
then
	0164: disable_marker $INJURED_PED_13_BLIP 
	0164: disable_marker $INJURED_PED_14_BLIP 
	0164: disable_marker $INJURED_PED_15_BLIP 
end
03C7: set_sensitivity_to_crime_to 1.0 
0004: $ONMISSION = 0 
0004: $ON_PARAMEDIC_MISSION = 0 
00D8: mission_has_finished 
goto @MISSION_END_AMBULANCE

//////////////////////////////////////////////////////////////////////
:CHUNK1_AMBULANCE
0227: $AMBULANCE_HEALTH_NOW = car $PLAYERS_AMBULANCE health
if
	001C:   $AMBULANCE_HEALTH_LAST > $AMBULANCE_HEALTH_NOW 
then
	0084: $TIME_DROP = $AMBULANCE_HEALTH_LAST 
	0060: $TIME_DROP -= $AMBULANCE_HEALTH_NOW 
	0010: $TIME_DROP *= 50 
	0060: $PED_TIME_LIMIT -= $TIME_DROP 
	0209: $RANDOM_SCREAM = random_int_in_ranges 0 2 
	if
		0038:   $PED_SEX_FLAG == 0
	then
		if
			0038:   $RANDOM_SCREAM == 0 
		then
			018C: play_sound 78 at $SOUND_X $SOUND_Y $SOUND_Z
		else
			018C: play_sound 79 at $SOUND_X $SOUND_Y $SOUND_Z
		end
	else
		if
			0038:   $RANDOM_SCREAM == 0 
		then
			018C: play_sound 80 at $SOUND_X $SOUND_Y $SOUND_Z
		else
			018C: play_sound 81 at $SOUND_X $SOUND_Y $SOUND_Z
		end
	end
	if
		001A:   0 > $PED_TIME_LIMIT
	then
		0004: $PED_TIME_LIMIT = 0 
	end
	0084: $AMBULANCE_HEALTH_LAST = $AMBULANCE_HEALTH_NOW
end
return
//////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////
:CHUNK2_AMBULANCE
01E9: $PEDS_IN_CAR = car $PLAYERS_AMBULANCE num_passengers 
01EA: $MAX_PEDS_IN_CAR = car $PLAYERS_AMBULANCE max_passengers 
if
	003A:   $PEDS_IN_CAR == $MAX_PEDS_IN_CAR 
then
	00BC: print_now 'A_FULL' time 5000 flag 1  // ~r~Ambulance full!!
	0004: $CAR_FULL_FLAG = 1 
else
	0004: $CAR_FULL_FLAG = 0
end
0227: $AMBULANCE_HEALTH_LAST = car $PLAYERS_AMBULANCE health 
return
//////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////
:CHUNK3_AMBULANCE
if
	0038:   $HOSPITAL_BLIP_FLAG == 0 
then
	018A: $HOSPITAL_BLIP = create_checkpoint_at $HOSPITAL_X $HOSPITAL_Y $HOSPITAL_Z 
	0004: $HOSPITAL_BLIP_FLAG = 1 
end
0084: $TIME_CHUNK_IN_SECS = $TIME_CHUNK 
0014: $TIME_CHUNK_IN_SECS /= 1000 
01E3: text_1number_styled 'A_TIME' number $TIME_CHUNK_IN_SECS time 6000 style 6  // +~1~ seconds
0058: $PED_TIME_LIMIT += $TIME_CHUNK 
return 
//////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////
:CHUNK4_AMBULANCE
00BA: print_big 'A_PASS' time 3000 style 5  // Rescued!
if
	0038:   $BONUS_TIME_FLAG == 1 
then
	0084: $TIME_CHUNK_IN_SECS = $TIME_CHUNK 
	0014: $TIME_CHUNK_IN_SECS /= 1000 
	01E3: text_1number_styled 'A_TIME' number $TIME_CHUNK_IN_SECS time 6000 style 6  // +~1~ seconds
	00DA: store_car_player_is_in $PLAYER_CHAR store_to $PLAYERS_AMBULANCE 
	0227: $PLAYERS_AMBULANCE_HEALTH = car $PLAYERS_AMBULANCE health 
	0008: $PLAYERS_AMBULANCE_HEALTH += 110 
	0224: set_car $PLAYERS_AMBULANCE health_to $PLAYERS_AMBULANCE_HEALTH 
	0058: $PED_TIME_LIMIT += $TIME_CHUNK 
	0008: $BONUS_TIME_FLAG += 1
end
018C: play_sound 94 at 0.0 0.0 0.0 
0008: $SAVED_PEDS += 1 
0401: increment_people_saved_in_ambulance 
return
//////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////
:PROGRESS_COUNTER1
030C: progress_made = 1 
0051: return 
//////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////
:PROGRESS_COUNTER2
030C: progress_made = 1 
0051: return 
//////////////////////////////////////////////////////////////////////
