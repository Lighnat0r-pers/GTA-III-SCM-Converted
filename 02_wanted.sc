// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***************************************WANTED INFO*************************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff
:M02_POLICEHELP
gosub @WANTED_INFO_START
gosub @WANTED_INFO_CLEANUP
end_thread

// ****************************************Mission Start************************************

:WANTED_INFO_START
03A4: name_thread 'WANTED' 
0004: $ONMISSION = 1 
0001: wait 0 ms 

//Set Variables
0004: $INFO_TIME_LAPSED = 0 
0004: $INFO_TIME_NOW = 0 
0004: $INFO_TIME_START = 0 
0004: $FLAG_INFO = 0 
0004: $FLAG_COPCAR_PROGRESS = 0 
0004: $FLAG_SWAT_PROGRESS = 0 
0004: $FLAG_INTRO_JUMP = 0 
0004: $WANTED_LEVEL = 0 

//Set Coords

//Mission Script
01C0: $WANTED_LEVEL = player $PLAYER_CHAR wanted_level 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
01B4: set_player $PLAYER_CHAR controllable 0 
03AF: set_streaming 0 
0247: request_model #ENFORCER 
0247: request_model #SWAT 
0247: request_model #DIABLOS 
0247: request_model #GANG06 
038B: load_all_models_now

while true
	if or
		8248:   NOT   model #ENFORCER available
		8248:   NOT   model #SWAT available
		8248:   NOT   model #GANG06 available
		8248:   NOT   model #DIABLOS available
	jf break
	wait 0 ms
end
03AF: set_streaming 1 
while 001A:   13 > $FLAG_INFO 
	wait 0 ms
	if
		0038:   $FLAG_INFO == 0 
	then
		01BD: $INFO_TIME_START = current_time_in_ms 
		00A5: create_car #POLICE at 1110.0 -823.0 15.0 store_to $COPCAR_INFO 
		0175: set_car $COPCAR_INFO z_angle_to 330.0 
		0129: $COP_INFO = create_actor PEDTYPE_CIVMALE #COP in_car $COPCAR_INFO driverseat 
		00A5: create_car #POLICE at 1105.0 -828.0 15.0 store_to $COPCAR2_INFO 
		0175: set_car $COPCAR2_INFO z_angle_to 330.0 
		0129: $COP2_INFO = create_actor PEDTYPE_CIVMALE #COP in_car $COPCAR2_INFO driverseat
		00A5: create_car #DIABLOS at 1115.0 -818.0 15.0 store_to $DIABLOCAR_INFO 
		0175: set_car $DIABLOCAR_INFO z_angle_to 0.0 
		0129: $DIABLO_INFO = create_actor PEDTYPE_GANG_DIABLO #GANG06 in_car $DIABLOCAR_INFO driverseat 
		01EB: set_car_density_to 0.25 
		03DE: set_ped_density_multiplier 0.0 
		03E5: text_box 'WANT_A'  // You will only be arrested if you have a ~h~wanted level.
		0004: $FLAG_INFO = 1
	end
	if
		0038:   $FLAG_INTRO_JUMP == 0
	then
		01BD: $INFO_TIME_NOW = current_time_in_ms 
		0084: $INFO_TIME_LAPSED = $INFO_TIME_NOW 
		0060: $INFO_TIME_LAPSED -= $INFO_TIME_START
	end
	if and
		0018:   $INFO_TIME_LAPSED > 3500 
		0038:   $FLAG_INFO == 1 
	then
		03E5: text_box 'WANT_G'  // When you are ~h~'busted'~w~ you are returned to the nearest police station.
		if and
			8119:   not car $COPCAR_INFO wrecked 
			8119:   not car $COPCAR2_INFO wrecked 
			8119:   not car $DIABLOCAR_INFO wrecked
		then
			00AD: set_car_cruise_speed $COPCAR2_INFO to 75.0 
			00AE: set_car_driving_style $COPCAR2_INFO to 3 
			032C: car $COPCAR2_INFO ram $DIABLOCAR_INFO 
			0397: car $COPCAR2_INFO siren = 1		
			00AD: set_car_cruise_speed $COPCAR_INFO to 70.0 
			00AE: set_car_driving_style $COPCAR_INFO to 3 
			032C: car $COPCAR_INFO ram $DIABLOCAR_INFO 
			0397: car $COPCAR_INFO siren = 1 
			0158: camera_on_vehicle $DIABLOCAR_INFO FIXED switchstyle JUMP_CUT
			00AD: set_car_cruise_speed $DIABLOCAR_INFO to 50.0 
			00AE: set_car_driving_style $DIABLOCAR_INFO to DRIVINGMODE_AVOIDCARS
			00A7: car_goto_coordinates $DIABLOCAR_INFO coords 982.0 -617.0 15.0 
		end
		015F: set_camera_position 1134.0 -695.0 18.0 0.0 rotation 0.0 0.0 
		0004: $FLAG_INFO = 2
	end
	if and
		0018:   $INFO_TIME_LAPSED > 5500 
		0038:   $FLAG_COPCAR_PROGRESS == 0
	then
		0395: clear_area 1 at 1142.0 -666.0 range 14.75 10.0 
		00A5: create_car #ENFORCER at 1142.0 -666.0 14.75 store_to $SWATVAN_INFO 
		0175: set_car $SWATVAN_INFO z_angle_to 90.0 
		020A: set_car $SWATVAN_INFO door_status_to CARLOCK_UNLOCKED
		00A9: car_set_idle $SWATVAN_INFO 
		009A: create_char PEDTYPE_CIVMALE model #SWAT at 1138.0 -671.0 15.0 store_to $SWAT2_INFO 
		009A: create_char PEDTYPE_CIVMALE model #SWAT at 1137.75 -661.25 15.0 store_to $SWAT1_INFO 
		01B2: give_actor $SWAT1_INFO weapon WEAPONTYPE_M16 ammo 60 
		0173: set_actor $SWAT2_INFO z_angle_to 110.0 
		0173: set_actor $SWAT1_INFO z_angle_to 80.0 
		01B2: give_actor $SWAT1_INFO weapon WEAPONTYPE_SHOTGUN ammo 10
		if
			8118:   not actor $DIABLO_INFO dead 
		then
			022C: set_actor $SWAT1_INFO to_look_at_actor $DIABLO_INFO
		end
		if
			8118:   not actor $COP_INFO dead 
		then
			022C: set_actor $SWAT2_INFO to_look_at_actor $COP_INFO
		end
		0004: $FLAG_COPCAR_PROGRESS = 1
	end
	if and
		0018:   $INFO_TIME_LAPSED > 7500 
		0038:   $FLAG_INFO == 2 
	then
		03E5: text_box 'WANT_H'  // The cops will take all your weapons and some of your cash as a bribe.
		0004: $FLAG_INFO = 3 
	end
	if and
		0018:   $INFO_TIME_LAPSED > 10500 
		0038:   $FLAG_INFO == 3 
	then
		03E5: text_box 'WANT_I'  // Any mission you were on will be failed.
		0004: $FLAG_INFO = 4 
	end
	if and
		0018:   $INFO_TIME_LAPSED > 12500 
		0038:   $FLAG_INFO == 4
	then
		03E5: text_box 'WANT_B'  // Your ~h~wanted level~w~ is represented by the row of stars in the top right of the screen.
		if
			8119:   not car $SWATVAN_INFO wrecked 
		then
			015F: set_camera_position 1135.75 -673.0 14.75 0.0 rotation 0.0 0.0 
			0158: camera_on_vehicle $SWATVAN_INFO FIXED switchstyle JUMP_CUT
			00A6: delete_car $COPCAR_INFO 
			00A6: delete_car $COPCAR2_INFO 
			00A6: delete_car $DIABLOCAR_INFO 
		end
		0004: $FLAG_INFO = 5
	end
	if and
		0018:   $INFO_TIME_LAPSED > 13500 
		0038:   $FLAG_INFO == 5
	then
		if and
			8119:   not car $SWATVAN_INFO wrecked 
			8118:   not actor $SWAT2_INFO dead
		then
			01D5: actor $SWAT2_INFO go_to_and_drive_car $SWATVAN_INFO
		end
		0004: $FLAG_INFO = 6
	end
	if and
		0018:   $INFO_TIME_LAPSED > 16000 
		0038:   $FLAG_INFO == 6
	then
		03E5: text_box 'WANT_C'  // You now have a ~h~wanted level~w~ of one...
		010D: set_player $PLAYER_CHAR wanted_level_to 1 
		if and
			8119:   not car $SWATVAN_INFO wrecked 
			8118:   not actor $SWAT1_INFO dead
		then
			01D4: actor $SWAT1_INFO go_to_car $SWATVAN_INFO and_enter_it_as_a_passenger
		end
		0004: $FLAG_INFO = 7
	end
	if and
		0018:   $INFO_TIME_LAPSED > 18000 
		0038:   $FLAG_INFO == 7
	then
		03E5: text_box 'WANT_D'  // two...
		010D: set_player $PLAYER_CHAR wanted_level_to 2 
		0004: $FLAG_INFO = 8 
	end
	if and
		0018:   $INFO_TIME_LAPSED > 20000 
		0038:   $FLAG_INFO == 8
	then
		03E5: text_box 'WANT_E'  // three...
		010D: set_player $PLAYER_CHAR wanted_level_to 3 
		0004: $FLAG_INFO = 9
	end
	if and
		0018:   $INFO_TIME_LAPSED > 22000 
		0038:   $FLAG_INFO == 9
	then
		03E5: text_box 'WANT_F'  // As your ~h~wanted level~w~ increases you will attract more powerful forms of law enforcement. 
		0004: $FLAG_INFO = 10
	end
	if and
		0018:   $INFO_TIME_LAPSED > 26000 
		0038:   $FLAG_INFO == 10
	then
		015F: set_camera_position 1135.0 -672.5 15.5 0.0 rotation 0.0 0.0 
		0157: camera_on_player $PLAYER_CHAR mode FIXED transition JUMP_CUT
		03E5: text_box 'WANT_J'  // You will find ways of reducing your wanted level the more you play.
		0213: $BRIBE_PICKUP = create_pickup #BRIBE type PICKUP_ON_STREET_SLOW at 1143.0 -671.0 15.0 
		0160: point_camera 1143.0 -671.0 15.0 switchstyle INTERPOLATION
		0004: $FLAG_INFO = 12
	end
	if and
		0018:   $INFO_TIME_LAPSED > 34000 
		0038:   $FLAG_INFO == 12
	then
		03E6: remove_text_box 
		0169: set_fade_color 0 0 0 
		016A: fade 0 for 1500 ms 
		while fading
			wait 0 ms
		end
		0215: destroy_pickup $BRIBE_PICKUP 
		009B: delete_char $DIABLO_INFO 
		00A6: delete_car $DIABLOCAR_INFO 
		009B: delete_char $COP_INFO 
		00A6: delete_car $COPCAR_INFO 
		009B: delete_char $COP2_INFO 
		00A6: delete_car $COPCAR2_INFO 
		009B: delete_char $SWAT1_INFO 
		009B: delete_char $SWAT2_INFO 
		00A6: delete_car $SWATVAN_INFO 
		02EB: restore_camera_jumpcut 
		01B4: set_player $PLAYER_CHAR controllable 1 
		010D: set_player $PLAYER_CHAR wanted_level_to $WANTED_LEVEL 
		0169: set_fade_color 0 0 0 
		016A: fade 1 for 1500 ms 
		while fading
			wait 0 ms
		end
		0004: $FLAG_INFO = 13
	end
	if and
		8119:   not car $SWATVAN_INFO wrecked 
		8118:   not actor $SWAT1_INFO dead 
		8118:   not actor $SWAT2_INFO dead 
	then
		if and
			0038:   $FLAG_SWAT_PROGRESS == 0 
			0018:   $INFO_TIME_LAPSED > 21000
		then
			if and
				00DB:   is_char_in_car $SWAT1_INFO car $SWATVAN_INFO 
				00DB:   is_char_in_car $SWAT2_INFO car $SWATVAN_INFO 
			then
				00AD: set_car_cruise_speed $SWATVAN_INFO to 10.0 
				00AE: set_car_driving_style $SWATVAN_INFO to DRIVINGMODE_AVOIDCARS
				02C2: car $SWATVAN_INFO drive_to_point 1133.0 -669.0 15.0 
				0004: $FLAG_SWAT_PROGRESS = 1 
			end
		end
	end
	if
		8119:   not car $SWATVAN_INFO wrecked 
	then
		if and
			01AD:   car $SWATVAN_INFO sphere 0 near_point 1133.0 -669.0 radius 3.0 3.0 
			0038:   $FLAG_SWAT_PROGRESS == 1 
			0018:   $INFO_TIME_LAPSED > 22000 
		then
			00A7: car_goto_coordinates $SWATVAN_INFO coords 982.0 -617.0 15.0 
			00AD: set_car_cruise_speed $SWATVAN_INFO to 30.0 
			00AE: set_car_driving_style $SWATVAN_INFO to 3 
			0397: car $SWATVAN_INFO siren = 1 
			0004: $FLAG_SWAT_PROGRESS = 2
		end
	end
	if and
		0038:   $FLAG_INTRO_JUMP == 0 
		001A:   12 > $FLAG_INFO
	if
		00E1:   is_button_pressed PAD1 button CROSS
	then
		0004: $INFO_TIME_LAPSED = 34001 
		0004: $FLAG_INFO = 12 
		0004: $FLAG_INTRO_JUMP = 1
	end
end
return

// mission cleanup

:WANTED_INFO_CLEANUP
02EB: restore_camera_jumpcut 
02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0
if
	8119:   not car $SWATVAN_INFO wrecked 
then
	00A8: car_wander_randomly $SWATVAN_INFO
end
if
	8119:   not car $COPCAR_INFO wrecked 
then
	00A8: car_wander_randomly $COPCAR_INFO
end
if
	8119:   not car $DIABLOCAR_INFO wrecked 
then
	00A8: car_wander_randomly $DIABLOCAR_INFO
end
01EB: set_car_density_to 1.0 
03DE: set_ped_density_multiplier 1.0 
01C3: remove_references_to_car $SWATVAN_INFO 
01C3: remove_references_to_car $COPCAR_INFO 
01C3: remove_references_to_car $DIABLOCAR_INFO 
01C2: remove_references_to_actor $COP_INFO 
01C2: remove_references_to_actor $SWAT1_INFO 
01C2: remove_references_to_actor $SWAT2_INFO 
01C2: remove_references_to_actor $DIABLO_INFO 
0249: release_model #COP 
0249: release_model #GANG06 
0249: release_model #SWAT 
0249: release_model #DIABLOS 
0249: release_model #ENFORCER 
0249: release_model #POLICE 
02BC: set_cop_behaviour 0
0004: $ONMISSION = 0 
0004: $FLAG_WANTED_INFO = 1 
00D8: mission_has_finished 
0051: return 
