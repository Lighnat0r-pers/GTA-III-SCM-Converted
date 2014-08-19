// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***************************************HEALTH INFO*************************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

:M01_HOSPITALHELP
gosub @HEALTH_INFO_START 
if
	0112:   has_deatharrest_been_executed 
then
	gosub @HEALTH_INFO_CLEANUP
end
gosub @HEALTH_INFO_CLEANUP
end_thread

:HEALTH_INFO_START
03A4: name_thread 'HEALTH' 
0004: $ONMISSION = 1 
0001: wait 0 ms 

//Set Variables
0004: $INFO_TIME_LAPSED = 0 
0004: $INFO_TIME_NOW = 0 
0004: $INFO_TIME_START = 0 
0004: $FLAG_INFO = 0 
0004: $FLAG_BOTTOM = 0 
0004: $WANTED_LEVEL = 0 
0004: $FLAG_INTRO_JUMP = 0 
//Set Coords



//Mission Script
01B4: set_player $PLAYER_CHAR controllable 0 
01C0: $WANTED_LEVEL = player $PLAYER_CHAR wanted_level 
0110: clear_player $PLAYER_CHAR wanted_level 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
0247: request_model #AMBULAN 
0247: request_model #MEDIC 
while true
	if or
		8248:   not model #AMBULAN available 
		8248:   not model #MEDIC available 
	jf break
	wait 0 ms
end

015F: set_camera_position 1138.563 -600.0 18.0 0.0 rotation 0.0 0.0 
0157: camera_on_player $PLAYER_CHAR mode FIXED transition INTERPOLATION
while 001A:   8 > $FLAG_INFO 
	wait 0 ms
	if
		0038:   $FLAG_INFO == 0 
	then
		01BD: $INFO_TIME_START = current_time_in_ms 
		0395: clear_area 1 at 1141.0 -622.0 range 14.75 30.0 
		0395: clear_area 1 at 1125.75 -594.0 range 14.75 10.0 
		01EB: set_car_density_to 0.0 
		03DE: set_ped_density_multiplier 0.0 
		00A5: create_car #AMBULAN at 1140.188 -621.5 14.75 store_to $WASTED_HELP_AMBULANCE 
		0175: set_car $WASTED_HELP_AMBULANCE z_angle_to 90.0 
		009A: create_char 4 model #MEDIC at 1136.75 -617.75 14.6875 store_to $WASTED_HELP_MEDIC 
		0173: set_actor $WASTED_HELP_MEDIC z_angle_to 25.0 
		009F: char_set_idle $WASTED_HELP_MEDIC 
		0350: set_actor $WASTED_HELP_MEDIC maintain_position_when_attacked 1 
		03E5: text_box 'HEAL_A'  // Your ~h~health~w~ is displayed in orange in the top right of the screen.
		03E7: flash_hud HUD_FLASH_HEALTH
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
		0018:   $INFO_TIME_LAPSED > 3000 
		001A:   2 > $FLAG_INFO 
	then
		03E7: flash_hud -1
	end
	if and
		0018:   $INFO_TIME_LAPSED > 5000
		0038:   $FLAG_INFO == 1
	then
		03E5: text_box 'HEAL_B'  // When you are ~h~'wasted'~w~ you are returned to the nearest hospital.
		0004: $FLAG_INFO = 2 
	end
	if and
		0018:   $INFO_TIME_LAPSED > 11000 
		0038:   $FLAG_INFO == 2 
	then
		if and
			8119:   not car $WASTED_HELP_AMBULANCE wrecked 
			8118:   not actor $WASTED_HELP_MEDIC dead
		then
			015F: set_camera_position 1133.0 -613.5 17.0 0.0 rotation 0.0 0.0 
			0158: camera_on_vehicle $WASTED_HELP_AMBULANCE FIXED switchstyle JUMP_CUT
			0350: set_actor $WASTED_HELP_MEDIC maintain_position_when_attacked 0 
			01D5: actor $WASTED_HELP_MEDIC go_to_and_drive_car $WASTED_HELP_AMBULANCE 
		end
		03E5: text_box 'HEAL_C'  // You will lose your weapons and the doctors will take some cash for patching you up.
		0004: $FLAG_INFO = 3
	end
	if and
		0018:   $INFO_TIME_LAPSED > 14000 
		0038:   $FLAG_INFO == 3 
	then
		03E5: text_box 'WANT_I'  // Any mission you were on will be failed.
		0004: $FLAG_INFO = 5
	end
	if and
		0018:   $INFO_TIME_LAPSED > 19500 
		0038:   $FLAG_INFO == 5 
	then
		03E5: text_box 'HEAL_E'  // You will find ways of healing or protecting yourself the more you play the game.
		015F: set_camera_position 1138.563 -600.0 18.0 0.0 rotation 0.0 0.0 
		0160: point_camera 1144.25 -603.5 15.0 switchstyle JUMP_CUT
		0213: $WASTED_HELP_HEALTH_PICKUP = create_pickup #HEALTH type PICKUP_ON_STREET_SLOW at 1144.25 -603.5 15.0 
		if
			8119:   not car $WASTED_HELP_AMBULANCE wrecked 
		then
			00A8: car_wander_randomly $WASTED_HELP_AMBULANCE
		end
		0004: $FLAG_INFO = 6 
	end
	if and
		0018:   $INFO_TIME_LAPSED > 22500 
		0038:   $FLAG_INFO == 6 
	then
		0160: point_camera 1147.0 -601.0625 15.0 switchstyle 1 
		0213: $WASTED_HELP_ARMOUR_PICKUP = create_pickup #BODYARMOUR type PICKUP_ON_STREET_SLOW at 1147.0 -601.0625 15.0 
		0004: $FLAG_INFO = 7
	end
	if and
		0018:   $INFO_TIME_LAPSED > 24000 
		0038:   $FLAG_INFO == 7 
	then
		0169: set_fade_color 0 0 0 
		016A: fade 0 for 1500 ms 
		03E6: remove_text_box
		while fading
			wait 0 ms
		end
		00A6: delete_car $WASTED_HELP_AMBULANCE 
		009B: delete_char $WASTED_HELP_MEDIC 
		0215: destroy_pickup $WASTED_HELP_HEALTH_PICKUP 
		0215: destroy_pickup $WASTED_HELP_ARMOUR_PICKUP 
		02EB: restore_camera_jumpcut 
		// 02A3: toggle_widescreen 0 // Removed by R*
		01B4: set_player $PLAYER_CHAR controllable 1 
		010D: set_player $PLAYER_CHAR wanted_level_to $WANTED_LEVEL 
		0169: set_fade_color 0 0 0 
		016A: fade 1 for 1500 ms 
		while fading
			wait 0 ms
		end
		0004: $FLAG_INFO = 8
	end
	if and
		0018:   $INFO_TIME_LAPSED > 16500 
		001A:   7 > $FLAG_INFO
	then
		if and
			8119:   not car $WASTED_HELP_AMBULANCE wrecked 
			8118:   not actor $WASTED_HELP_MEDIC dead 
		then
			if and
				00DB:   is_char_in_car $WASTED_HELP_MEDIC car $WASTED_HELP_AMBULANCE 
				0038:   $FLAG_BOTTOM == 0 
			then
				00AD: set_car_cruise_speed $WASTED_HELP_AMBULANCE to 40.0 
				00AE: set_car_driving_style $WASTED_HELP_AMBULANCE to DRIVINGMODE_AVOIDCARS
				0397: car $WASTED_HELP_AMBULANCE siren = 1 
				00A7: car_goto_coordinates $WASTED_HELP_AMBULANCE coords 1023.0 -480.0 19.6875 
				0004: $FLAG_BOTTOM = 1 
			end
		end
	end
	if and
		0038:   $FLAG_INTRO_JUMP == 0 
		001A:   7 > $FLAG_INFO 
	then
		if
			00E1:   is_button_pressed 0 button 16
		then
			0004: $INFO_TIME_LAPSED = 24001 
			0004: $FLAG_INFO = 7 
			0004: $FLAG_INTRO_JUMP = 1 
		end
	end
end // while
return

// mission cleanup
:HEALTH_INFO_CLEANUP
02EB: restore_camera_jumpcut 
02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
01C3: remove_references_to_car $WASTED_HELP_AMBULANCE 
01C2: remove_references_to_actor $WASTED_HELP_MEDIC 
0249: release_model #AMBULAN 
0249: release_model #MEDIC 
01EB: set_car_density_to 1.0 
03DE: set_ped_density_multiplier 1.0 
// 0215: remove_pickup $WASTED_INFO_PICKUP // Removed by R*
0004: $ONMISSION = 0 
0004: $FLAG_HEALTH_INFO = 1 
00D8: mission_has_finished 
0051: return
