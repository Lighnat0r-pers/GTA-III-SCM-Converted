// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Fire missions *********************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

:M12_FIREFIGHTER
gosub @MISSION_START_FIRE
gosub @FIRETRUCK_FAILED

:MISSION_END_FIRE
end_thread

// ****************************************Mission Start************************************

:MISSION_START_FIRE
0004: $ONMISSION = 1 
0004: $ON_FIREFIGHTER_MISSION = 1 
0001: wait 0 ms 
03A4: name_thread 'FIRETRU' 
0004: $FIRE_TIME_LIMIT = 0 
0004: $FIRE_TO_EXTINGUISH = 0 
0004: $FIRE_TO_EXTINGUISH_BLIP = 0 
0004: $CAR_ON_FIRE = 0 
0004: $RANDOM_CAR_MODEL = 0 
0004: $CAR_ON_FIRE_CREATED = 0 
0004: $UNLOCK_OUTOFRANGE_MECHANIC = 0
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	0004: $PLAYER_IN_RANGE_FIRE = 0 
	0004: $FLAG_GOT_RANGE_MSSG = 0 
end
0004: $SCORE_FT = 250 
0004: $DISPLAYED_TIMER = 0 
0004: $FIRES_EXTINGUISHED = 0 
0004: $DISPLAYED_COUNTER = 0 
0004: $TOTAL_SCORE = 0 
0004: $FIRST_FIRE_FLAG = 0 
0004: $MISSION_END_BUTTON_FT = 0 
0004: $FIRE_LOCATION = 0 
0005: $TIME_DIVIDER_DIVIDER = 2.0 


:NEXT_FIRE
0001: wait 0 ms 
03C7: set_sensitivity_to_crime_to 0.5 
0054: get_player_coordinates $PLAYER_CHAR store_to $PLAYER1_X $PLAYER1_Y $PLAYER1_Z 

gosub @CHECK_FAILURE_CRITERIA

if
	03C6:   current_island == LEVEL_INDUSTRIAL 
then
	0208: $RANDOM_FIRE_X = random_float 778.0 1540.0 
	0208: $RANDOM_FIRE_Y = random_float -1110.0 190.0 
	0004: $FIRE_LOCATION = 1 
	0005: $TIME_DIVIDER = 13.0
end

if
	03C6:   current_island == LEVEL_COMMERCIAL
then
	0208: $RANDOM_FIRE_X = random_float -192.0 545.0 
	0208: $RANDOM_FIRE_Y = random_float -1626.0 98.0 
	0004: $FIRE_LOCATION = 2 
	0005: $TIME_DIVIDER = 14.0 
end

if
	03C6:   current_island == LEVEL_SUBURBAN
then
	0208: $RANDOM_FIRE_X = random_float -1300.0 -414.0 
	0208: $RANDOM_FIRE_Y = random_float -608.75 380.0 
	0004: $FIRE_LOCATION = 3 
	0005: $TIME_DIVIDER = 11.0 
end
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	0004: $FLAG_GOT_RANGE_MSSG = 0 
	0004: $PLAYER_IN_RANGE_FIRE = 1 
end

if and
	0038:   $UNLOCKEXTRAS1 == 1
	0038:   $UNLOCK_OUTOFRANGE_MECHANIC == 1
then
	gosub @FIRETRUCK_CHECK_RANGE
end

02C1: set $FIRE_COORD_X $FIRE_COORD_Y $FIRE_COORD_Z to_car_path_coords_closest_to $RANDOM_FIRE_X $RANDOM_FIRE_Y $PLAYER1_Z 

if and // CALAHAN BRIDGE
	0020:   $FIRE_COORD_X > 670.0 
	0022:   1035.0 > $FIRE_COORD_X 
	0020:   $FIRE_COORD_Y > -953.0 
	0022:   -912.0 > $FIRE_COORD_Y 
then
	goto @NEXT_FIRE
end
if and // TUNNEL ENTRANCE INDUSTRIAL
	0020:   $FIRE_COORD_X > 649.0 
	0022:   1066.0 > $FIRE_COORD_X 
	0020:   $FIRE_COORD_Y > 25.0 
	0022:   217.0 > $FIRE_COORD_Y 
then
	goto @NEXT_FIRE
end
if and // AIRPORT SUBURBAN
	0020:   $FIRE_COORD_X > -1611.5 
	0022:   -745.25 > $FIRE_COORD_X 
	0020:   $FIRE_COORD_Y > -1001.875 
	0022:   -371.1875 > $FIRE_COORD_Y
then
	goto @NEXT_FIRE
end
if and // BAIT WAREHOUSE CARPARK SUBURBIA
	0020:   $FIRE_COORD_X > -1255.375 
	0022:   -1187.875 > $FIRE_COORD_X 
	0020:   $FIRE_COORD_Y > 80.5625 
	0022:   123.375 > $FIRE_COORD_Y 
then
	goto @NEXT_FIRE
end
if and // FRANKIES HOUSE
	0020:   $FIRE_COORD_X > 1386.375 
	0022:   1475.75 > $FIRE_COORD_X 
	0020:   $FIRE_COORD_Y > -292.0625 
	0022:   -168.0 > $FIRE_COORD_Y 
then
	goto @NEXT_FIRE
end
if
	0038:   $FIRE_LOCATION == 1 
then
	if or // INDUSTRIAL
		8020:   not  $FIRE_COORD_X > 778.0 
		8022:   not  1540.0 > $FIRE_COORD_X 
		8020:   not  $FIRE_COORD_Y > -1110.0 
		8022:   not  190.0 > $FIRE_COORD_Y 
	then
		goto @NEXT_FIRE
	end
end
if
	0038:   $FIRE_LOCATION == 2
then
	if or // COMMERCIAL
		8020:   not  $FIRE_COORD_X > -192.0 
		8022:   not  545.0 > $FIRE_COORD_X 
		8020:   not  $FIRE_COORD_Y > -1626.0 
		8022:   not  98.0 > $FIRE_COORD_Y
	then
		goto @NEXT_FIRE
	end
end
if
	0038:   $FIRE_LOCATION == 3
then
	if or // SUBURBIA
		8020:   not  $FIRE_COORD_X > -1300.0 
		8022:   not  -414.0 > $FIRE_COORD_X 
		8020:   not  $FIRE_COORD_Y > -608.75 
		8022:   not  380.0 > $FIRE_COORD_Y 
	then
		goto @NEXT_FIRE
	end
end
if 
	0022:   -1.0 > $FIRE_COORD_Z 
then
	goto @NEXT_FIRE
end

0086: $DIFFERENCE_X_FLOAT = $PLAYER1_X 
0061: $DIFFERENCE_X_FLOAT -= $FIRE_COORD_X 
0086: $DIFFERENCE_Y_FLOAT = $PLAYER1_Y 
0061: $DIFFERENCE_Y_FLOAT -= $FIRE_COORD_Y 
0069: $DIFFERENCE_X_FLOAT *= $DIFFERENCE_X_FLOAT 
0069: $DIFFERENCE_Y_FLOAT *= $DIFFERENCE_Y_FLOAT 
0086: $SUM_DIFFERENCE_XY = $DIFFERENCE_X_FLOAT 
0059: $SUM_DIFFERENCE_XY += $DIFFERENCE_Y_FLOAT 
01FB: $PLAYERS_DISTANCE_FROM_FIRE = square_root $SUM_DIFFERENCE_XY 
if 
	0022:   200.0 > $PLAYERS_DISTANCE_FROM_FIRE
then
	goto @NEXT_FIRE
end

0086: $FIRE_TIME_LIMIT_FLOAT = $PLAYERS_DISTANCE_FROM_FIRE 
0071: $FIRE_TIME_LIMIT_FLOAT /= $TIME_DIVIDER 
0059: $TIME_DIVIDER += $TIME_DIVIDER_DIVIDER 
0009: $TIME_DIVIDER_DIVIDER += 2.0 
0011: $FIRE_TIME_LIMIT_FLOAT *= 1000.0 
008C: $INTERMEDIATE_INT = float_to_integer $FIRE_TIME_LIMIT_FLOAT 
0058: $FIRE_TIME_LIMIT += $INTERMEDIATE_INT 
if
	0038:   $FIRES_EXTINGUISHED == 0 
then
	if
		001A:   50000 > $FIRE_TIME_LIMIT 
	then
		0004: $FIRE_TIME_LIMIT = 50000 
	end
end

:GENERATE_MODEL
0209: $RANDOM_CAR_MODEL = random_int_in_ranges 90 140 //INC 90 NOT INC 140
if and
	0018:   $RANDOM_CAR_MODEL > 113 // CAR_BFINJECTION CAR_CORPSE CAR_POLICE CAR_ENFORCER CAR_SECURICAR CAR_BANSHEE BOAT_PREDATOR CAR_BUS
	001A:   128 > $RANDOM_CAR_MODEL // CAR_RHINO CAR_BARRACKS TRAIN_SUBWAY HELI_POLICE PLANE_DODO CAR_COACH
then
	goto @GENERATE_MODEL
end
if or
	0038:   $RANDOM_CAR_MODEL == CAR_STINGER
	0038:   $RANDOM_CAR_MODEL == CAR_FIRETRUCK
	0038:   $RANDOM_CAR_MODEL == CAR_INFERNUS
	0038:   $RANDOM_CAR_MODEL == CAR_CHEETAH
then
	goto @GENERATE_MODEL
end
if or
	0038:   $RANDOM_CAR_MODEL == CAR_AMBULANCE
	0038:   $RANDOM_CAR_MODEL == CAR_FBI
	0038:   $RANDOM_CAR_MODEL == CAR_RCBANDIT
	0038:   $RANDOM_CAR_MODEL == PLANE_AIRTRAIN
then
	goto @GENERATE_MODEL
end

0247: request_model $RANDOM_CAR_MODEL 
if
	0038:   $FIRST_FIRE_FLAG == 1
then
	0006: 16@ = 0 
else
	0006: 16@ = 3001 
	0004: $FIRST_FIRE_FLAG = 1
end

while true
	if or
		8248:   not model $RANDOM_CAR_MODEL available 
		8019:   not  16@ > 3000 
	jf break
	wait 0 ms
	if
		001A:   1 > $FIRE_TIME_LIMIT 
	then
		goto @FIRETRUCK_FAILED
	end
	gosub @CHECK_FAILURE_CRITERIA
end //while

0208: $RANDOM_CAR_HEADING = random_float 0.0 359.875 
if
	0038:   $CAR_ON_FIRE_CREATED == 0 
then
	00A5: $CAR_ON_FIRE = create_car $RANDOM_CAR_MODEL at $FIRE_COORD_X $FIRE_COORD_Y $FIRE_COORD_Z
	0004: $CAR_ON_FIRE_CREATED = 1 
end

0129: $DUMMY_PED_FOR_ZONE = create_actor PEDTYPE_CIVMALE #MALE01 in_car $CAR_ON_FIRE driverseat
0249: release_model $RANDOM_CAR_MODEL 
0175: set_car $CAR_ON_FIRE z_angle_to $RANDOM_CAR_HEADING 
0325: set_car_on_fire $CAR_ON_FIRE store_to $FIRE_TO_EXTINGUISH
0186: $FIRE_TO_EXTINGUISH_BLIP = create_marker_above_car $CAR_ON_FIRE
00A9: car_set_idle $CAR_ON_FIRE 
009F: char_set_idle $DUMMY_PED_FOR_ZONE 
00AD: set_car_cruise_speed $CAR_ON_FIRE to 0.0 
02AA: set_car $CAR_ON_FIRE immune_to_nonplayer 1 

if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'PORT_W'  // Callahan Point
then
	0384: text_1string 'F_START' string 'PORT_W' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Callahan Point
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'PORT_S'  // Atlantic Quays
then
	0384: text_1string 'F_START' string 'PORT_S' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Atlantic Quays
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'PORT_E'  // Portland Harbor
then
	0384: text_1string 'F_START' string 'PORT_E' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Portland Harbor
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'PORT_I'  // Trenton
then
	0384: text_1string 'F_START' string 'PORT_I' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Trenton
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'S_VIEW'  // Portland View
then
	0384: text_1string 'F_START' string 'S_VIEW' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Portland View
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'CHINA'  // Chinatown
then
	0384: text_1string 'F_START' string 'CHINA' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Chinatown
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'EASTBAY'  // Portland Beach
then
	0384: text_1string 'F_START' string 'EASTBAY' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Portland Beach
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'LITTLEI'  // Saint Mark's
then
	0384: text_1string 'F_START' string 'LITTLEI' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Saint Mark's
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'REDLIGH'  // Red Light District
then
	0384: text_1string 'F_START' string 'REDLIGH' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Red Light District
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'TOWERS'  // Hepburn Heights
then
	0384: text_1string 'F_START' string 'TOWERS' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Hepburn Heights
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'HARWOOD'  // Harwood
then
	0384: text_1string 'F_START' string 'HARWOOD' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Harwood
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'ROADBR1'  // Callahan Bridge
then
	0384: text_1string 'F_START' string 'ROADBR1' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Callahan Bridge
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'ROADBR2'  // Callahan Bridge
then
	0384: text_1string 'F_START' string 'ROADBR2' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Callahan Bridge
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'STADIUM'  // Aspatria
then
	0384: text_1string 'F_START' string 'STADIUM' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Aspatria
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'HOSPI_2'  // Rockford
then
	0384: text_1string 'F_START' string 'HOSPI_2' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Rockford
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'UNIVERS'  // Liberty Campus
then
	0384: text_1string 'F_START' string 'UNIVERS' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Liberty Campus
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'CONSTRU'  // Fort Staunton
then
	0384: text_1string 'F_START' string 'CONSTRU' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Fort Staunton
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'PARK'  // Belleville Park
then
	0384: text_1string 'F_START' string 'PARK' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Belleville Park
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'COM_EAS'  // Newport
then
	0384: text_1string 'F_START' string 'COM_EAS' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Newport
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'SHOPING'  // Bedford Point
then
	0384: text_1string 'F_START' string 'SHOPING' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Bedford Point
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'YAKUSA'  // Torrington
then
	0384: text_1string 'F_START' string 'YAKUSA' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Torrington
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'AIRPORT'  // Francis Intl. Airport
then
	0384: text_1string 'F_START' string 'AIRPORT' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Francis Intl. Airport
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'PROJECT'  // Wichita Gardens
then
	0384: text_1string 'F_START' string 'PROJECT' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Wichita Gardens
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'SUB_IND'  // Pike Creek
then
	0384: text_1string 'F_START' string 'SUB_IND' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Pike Creek
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'SWANKS'  // Cedar Grove
then
	0384: text_1string 'F_START' string 'SWANKS' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Cedar Grove
end
if
	0154:   actor $DUMMY_PED_FOR_ZONE in_zone 'BIG_DAM'  // Cochrane Dam
then
	0384: text_1string 'F_START' string 'BIG_DAM' duration 5000 ms flag 1  // ~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire. // Cochrane Dam
end
009B: delete_char $DUMMY_PED_FOR_ZONE 

if
	0038:   $DISPLAYED_TIMER == 0 
then
	014E: start_timer_at $FIRE_TIME_LIMIT 
	0004: $DISPLAYED_TIMER = 1
end
03E6: remove_text_box 
if
	0038:   $GOT_SIREN_HELP_BEFORE == 1
then
	03E5: text_box 'SIREN_1'  // To turn on this vehicle's sirens tap the ~h~~k~~VEHICLE_HORN~ button~w~.
	0004: $GOT_SIREN_HELP_BEFORE = 2
end
if
	0038:   $GOT_SIREN_HELP_BEFORE == 0
then
	03E5: text_box 'SPRAY_1'  // Use the ~h~~k~~PED_FIREWEAPON~ button ~w~to fire the water cannon.
	0004: $GOT_SIREN_HELP_BEFORE = 1
end

while 82D0:   not fire $FIRE_TO_EXTINGUISH extinguished 
	wait 0 ms
	if or
		0119:   car $CAR_ON_FIRE wrecked
		001A:   1 > $FIRE_TIME_LIMIT
	then
		00BC: print_now 'F_FAIL2' duration 5000 ms flag 1  // ~r~You're too late!
		goto @FIRETRUCK_FAILED
	end
	0227: $CAR_ON_FIRE_HEALTH = car $CAR_ON_FIRE health
	if
		001A:   900 > $CAR_ON_FIRE_HEALTH 
	then
		020B: explode_car $CAR_ON_FIRE
	end
	gosub @CHECK_FAILURE_CRITERIA
end //while
goto @FIRETRUCK_PASSED

///////////////////////////

:FIRETRUCK_CHECK_RANGE
return


///////////////////////////

:CHECK_FAILURE_CRITERIA
if
	80DE:   not is_player_in_model $PLAYER_CHAR model #FIRETRUK 
then
	00BC: print_now 'F_CANC' duration 3000 ms flag 1  // ~r~Fire Fighter mission cancelled!
	goto @FIRETRUCK_FAILED
end

0293: $CONTROLMODE = get_controller_mode 
if
	8038:   not  $CONTROLMODE == 3 
then
	if
		00E1:   is_button_pressed PAD1 button RIGHTSHOCK
	then
		0004: $MISSION_END_BUTTON_FT = 1 
	end
else
	if
		00E1:   is_button_pressed PAD1 button SQUARE
	then
		0004: $MISSION_END_BUTTON_FT = 1 
	end
end
if
	0038:   $MISSION_END_BUTTON_FT == 1
then
	if
		8038:   not  $CONTROLMODE == 3 
	then
		if
			80E1:   not is_button_pressed PAD1 button RIGHTSHOCK
		then
			00BC: print_now 'F_CANC' duration 3000 ms flag 1  // ~r~Fire Fighter mission cancelled!
			goto @FIRETRUCK_FAILED
		end
	else
		if
			80E1:   not is_button_pressed PAD1 button SQUARE
		then
			00BC: print_now 'F_CANC' duration 3000 ms flag 1  // ~r~Fire Fighter mission cancelled!
			goto @FIRETRUCK_FAILED
		end
	end
end
return

///////////////////////////

:FIRETRUCK_PASSED
0008: $FIRES_EXTINGUISHED += 1 
00BA: print_big 'F_PASS1' duration 5000 ms style 5  // Fire extinguished!
01E3: text_1number_styled 'REWARD' number $SCORE_FT duration 6000 ms style 6  // REWARD $~1~
0404: increment_fires_extinguished 
if
	0038:   $FIRE_LOCATION == 1
then
	0008: $IND_FIRES_EXTING += 1
end
if
	0038:   $FIRE_LOCATION == 2
then
	0008: $COM_FIRES_EXTING += 1
end
if
	0038:   $FIRE_LOCATION == 3
then
	0008: $SUB_FIRES_EXTING += 1
end
if and
	0038:   $EARNED_FREE_FLAMETHROWER == 0
	0018:   $IND_FIRES_EXTING > 19 
	0018:   $COM_FIRES_EXTING > 19 
	0018:   $SUB_FIRES_EXTING > 19 
then
	014D: text_pager 'PAGEB11' 140 100 1  // Flamethrower delivered to hideout
	030C: set_mission_points += 1 
	0004: $EARNED_FREE_FLAMETHROWER = 1 
end
0109: player $PLAYER_CHAR money += $SCORE_FT 
0058: $TOTAL_SCORE += $SCORE_FT 
0008: $SCORE_FT += 250 
031A: remove_all_fires 
0164: disable_marker $FIRE_TO_EXTINGUISH_BLIP 
018C: play_sound SOUND_PART_MISSION_COMPLETE at 0.0 0.0 0.0 
if
	00E0:   is_player_in_any_car $PLAYER_CHAR 
then
	00DA: $PLAYERS_FIRETRUCK = store_car_player_is_in $PLAYER_CHAR
	0227: $PLAYERS_FIRETRUCK_HEALTH = car $PLAYERS_FIRETRUCK health 
	0008: $PLAYERS_FIRETRUCK_HEALTH += 150 
	0224: set_car $PLAYERS_FIRETRUCK health_to $PLAYERS_FIRETRUCK_HEALTH
end
if
	0038:   $DISPLAYED_COUNTER == 0 
then
	03C4: set_status_text_to $FIRES_EXTINGUISHED COUNTER_DISPLAY_NUMBER 'F_EXTIN'  // FIRES:
	0004: $DISPLAYED_COUNTER = 1 
end
gosub @REMOVE_CAR_IF_NEEDED
goto @NEXT_FIRE

///////////////////////////

:FIRETRUCK_FAILED
014F: stop_timer $FIRE_TIME_LIMIT 
0151: remove_status_text $FIRES_EXTINGUISHED 
00BA: print_big 'F_FAIL1' time 5000 style 5  // Fire Truck mission ended.
01E3: text_1number_styled 'TSCORE' number $TOTAL_SCORE duration 6000 ms style 6  // EARNINGS: $~1~
031A: remove_all_fires 
0164: disable_marker $FIRE_TO_EXTINGUISH_BLIP 
03E6: remove_text_box 
03C7: set_sensitivity_to_crime_to 1.0 
gosub @REMOVE_CAR_IF_NEEDED
0004: $ONMISSION = 0 
0004: $ON_FIREFIGHTER_MISSION = 0 
00D8: mission_has_finished 
goto @MISSION_END_FIRE

///////////////////////////

:REMOVE_CAR_IF_NEEDED
if
	0038:   $CAR_ON_FIRE_CREATED == 1
then	
	if
		8119:   not car $CAR_ON_FIRE wrecked
	then
		02AA: set_car $CAR_ON_FIRE immune_to_nonplayer 0 
	end
	01C3: remove_references_to_car $CAR_ON_FIRE
	0004: $CAR_ON_FIRE_CREATED = 0 
end
0249: release_model $RANDOM_CAR_MODEL
return

///////////////////////////
