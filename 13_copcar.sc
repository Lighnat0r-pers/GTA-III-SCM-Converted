// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *********************************** Cop Car Mission ************************************* 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff
:M13_VIGILANTE
gosub @MISSION_START_COP_CAR
gosub @COP_CAR_FAILED

:MISSION_END_COP_CAR
end_thread

// ****************************************Mission Start************************************

:MISSION_START_COP_CAR
0004: $ONMISSION = 1 
0004: $ON_VIGILANTE_MISSION = 1 
0004: $VIGILANTE_BONUS_KILLS = 5 
03A4: name_thread 'COPCAR' 
0004: $TOTAL_CRIMINALS_KILLED = 0 
0004: $GOT_COP_BREIF = 0 
0001: wait 0 ms 

:NEXT_COP_CAR
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	0004: $GOT_RANGE_MESSAGE = 0 
	0004: $PLAYER_IN_RANGE = 0 
end
0004: $CAR_MODEL = 0 
0004: $CRIMINAL_CAR = 0 
0004: $CRIMINAL_CREATED_FLAG = 0 
0004: $CRIMINAL = 0 
0004: $CRIMINAL_BLIP = 0 
0004: $RANDOM_GUN = 0 
0004: $COP_TIME_LIMIT = 0 
0004: $GOT_CAR_CRIM_IS_IN = 0 
0004: $TIMER_RESET_FLAG = 0 
0004: $GAME_TIME_FLAG = 0 
0004: $MISSION_END_BUTTON = 0 
0004: $LOCATION = 0 
0004: $COPCAR_CANCELLED_FLAG = 0 
0005: $PLAYER_C_X = 0.0 
0005: $PLAYER_C_Y = 0.0 
0005: $PLAYER_C_Z = 0.0 
0005: $RANDOM_CRIM_X = 0.0 
0005: $RANDOM_CRIM_Y = 0.0 
0005: $CRIMINAL_COORD_X = 0.0 
0005: $CRIMINAL_COORD_Y = 0.0 
0005: $CRIMINAL_COORD_Z = 0.0 
0005: $DIFF_X_FLOAT = 0.0 
0005: $DIFF_Y_FLOAT = 0.0 
0005: $SUM_OF_DIFF_XY = 0.0 
0005: $PLAYERS_DISTANCE_FROM_CRIMINAL = 0.0 
0005: $COP_TIME_LIMIT_FLOAT = 0.0 
0005: $CRIMINAL_HEADING = 0.0 
0054: get_player_coordinates $PLAYER_CHAR store_to $PLAYER_C_X $PLAYER_C_Y $PLAYER_C_Z 
0247: request_model #SENTINEL 

:CRIMINAL_IN_CAR
wait 0 ms
if
	0038:   $GOT_COP_BREIF == 0 
then
	00BC: print_now 'LEGAL' time 3000 flag 1  // ~g~Eliminate the criminal threat!
	0006: 17@ = 0 
	0004: $GOT_COP_BREIF = 1
else
	0006: 17@ = 3000
end
if
	03C6:   current_island == LEVEL_INDUSTRIAL 
then
	0208: $RANDOM_CRIM_X = random_float 778.0 1540.0 
	0208: $RANDOM_CRIM_Y = random_float -1110.0 190.0 
	0004: $LOCATION = 1 
end
if
	03C6:   current_island == LEVEL_COMMERCIAL
then
	0208: $RANDOM_CRIM_X = random_float -192.0 545.0 
	0208: $RANDOM_CRIM_Y = random_float -1626.0 98.0 
	0004: $LOCATION = 2 
end
if
	03C6:   current_island == LEVEL_SUBURBAN
then
	0208: $RANDOM_CRIM_X = random_float -1300.0 -414.0 
	0208: $RANDOM_CRIM_Y = random_float -608.75 380.0 
	0004: $LOCATION = 3
end
if
	0038:   $UNLOCKEXTRAS1 == 1
then
	0004: $GOT_RANGE_MESSAGE = 0 
	0004: $PLAYER_IN_RANGE = 1
end
if and
	0038:   $UNLOCKEXTRAS1 == 1
	0038:   $UNLOCK_OUTOFRANGE_MECHANIC == 1
then
	gosub @COP_CAR_CHECK_RANGE
end

gosub @COPCAR_CANCELLED_CHECKS

02C1: set $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z to_car_path_coords_closest_to $RANDOM_CRIM_X $RANDOM_CRIM_Y $PLAYER_C_Z

if and // COLOMBIAN BOAT
	0020:   $CRIMINAL_COORD_X > 1398.0 
	0022:   1615.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -965.0 
	0022:   -902.0 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // BACK OF LUIGI'S
	0020:   $CRIMINAL_COORD_X > 879.0 
	0022:   892.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -427.0 
	0022:   -407.0 > $CRIMINAL_COORD_Y  
then
	goto @CRIMINAL_IN_CAR
end
if and // FISH FACTORY
	0020:   $CRIMINAL_COORD_X > 944.75 
	0022:   1017.063 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -1148.75 
	0022:   -1076.563 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // CHINATOWN MARKET
	0020:   $CRIMINAL_COORD_X > 920.75 
	0022:   1004.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -754.1875 
	0022:   -670.0 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // CALAHAN BRIDGE
	0020:   $CRIMINAL_COORD_X > 670.0 
	0022:   1035.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -953.0 
	0022:   -912.0 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // DOCKS INDUSTRIAL
	0020:   $CRIMINAL_COORD_X > 1364.0 
	0022:   1641.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -1165.0 
	0022:   -617.0 > $CRIMINAL_COORD_Y
then
	goto @CRIMINAL_IN_CAR
end
if and // TUNNEL ENTRANCE INDUSTRIAL
	0020:   $CRIMINAL_COORD_X > 649.0 
	0022:   1066.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > 25.0 
	0022:   217.0 > $CRIMINAL_COORD_Y
then
	goto @CRIMINAL_IN_CAR
end
if and // AIRPORT SUBURBAN
	0020:   $CRIMINAL_COORD_X > -1611.5 
	0022:   -745.25 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -1001.875 
	0022:   -371.1875 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // OLD SCHOOL HALL AND PARK AREA
	0020:   $CRIMINAL_COORD_X > 939.75 
	0022:   1035.563 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -901.25 
	0022:   -828.1875 > $CRIMINAL_COORD_Y
then
	goto @CRIMINAL_IN_CAR
end
if and // DOG FOOD FACTORY
	0020:   $CRIMINAL_COORD_X > 1215.25 
	0022:   1223.688 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -839.375 
	0022:   -763.5625 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // INDUSTRIAL SAFEHOUSE
	0020:   $CRIMINAL_COORD_X > 845.25 
	0022:   899.5625 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -312.5625 
	0022:   -295.6875 > $CRIMINAL_COORD_Y
then
	goto @CRIMINAL_IN_CAR
end
if and // DOJO COMMERCIAL
	0020:   $CRIMINAL_COORD_X > 113.25 
	0022:   99.6875 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -1284.75 
	0022:   -1273.0 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // COLOMBIAN OJG COMPOUND
	0020:   $CRIMINAL_COORD_X > 18.3125 
	0022:   92.0 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -388.6875 
	0022:   -312.375 > $CRIMINAL_COORD_Y
then
	goto @CRIMINAL_IN_CAR
end
if and // BAIT WAREHOUSE CARPARK SUBURBIA
	0020:   $CRIMINAL_COORD_X > -1255.375 
	0022:   -1187.875 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > 80.5625 
	0022:   123.375 > $CRIMINAL_COORD_Y 
then
	goto @CRIMINAL_IN_CAR
end
if and // FRANKIES HOUSE
	0020:   $CRIMINAL_COORD_X > 1386.375 
	0022:   1475.75 > $CRIMINAL_COORD_X 
	0020:   $CRIMINAL_COORD_Y > -292.0625 
	0022:   -168.0 > $CRIMINAL_COORD_Y
then
	goto @CRIMINAL_IN_CAR
end

if
	0038:   $LOCATION == 1 
then
	if or // INDUSTRIAL
		8020:   not  $CRIMINAL_COORD_X > 778.0 
		8022:   not  1540.0 > $CRIMINAL_COORD_X 
		8020:   not  $CRIMINAL_COORD_Y > -1110.0 
		8022:   not  190.0 > $CRIMINAL_COORD_Y 
	then
		goto @CRIMINAL_IN_CAR
	end
end
if
	0038:   $LOCATION == 2
then
	if or // COMMERCIAL
		8020:   not  $CRIMINAL_COORD_X > -192.0 
		8022:   not  545.0 > $CRIMINAL_COORD_X 
		8020:   not  $CRIMINAL_COORD_Y > -1626.0 
		8022:   not  98.0 > $CRIMINAL_COORD_Y
	then
		goto @CRIMINAL_IN_CAR
	end
end
if
	0038:   $LOCATION == 3
then
	if or // SUBURBIA
		8020:   not  $CRIMINAL_COORD_X > -1300.0 
		8022:   not  -414.0 > $CRIMINAL_COORD_X 
		8020:   not  $CRIMINAL_COORD_Y > -608.75 
		8022:   not  380.0 > $CRIMINAL_COORD_Y 
	then
		goto @CRIMINAL_IN_CAR
	end
end
if 
	0022:   -1.0 > $CRIMINAL_COORD_Z 
then
	goto @CRIMINAL_IN_CAR
end

0086: $DIFF_X_FLOAT = $PLAYER_C_X 
0061: $DIFF_X_FLOAT -= $CRIMINAL_COORD_X 
0086: $DIFF_Y_FLOAT = $PLAYER_C_Y 
0061: $DIFF_Y_FLOAT -= $CRIMINAL_COORD_Y 
0069: $DIFF_X_FLOAT *= $DIFF_X_FLOAT 
0069: $DIFF_Y_FLOAT *= $DIFF_Y_FLOAT 
0086: $SUM_OF_DIFF_XY = $DIFF_X_FLOAT 
0059: $SUM_OF_DIFF_XY += $DIFF_Y_FLOAT 
01FB: $PLAYERS_DISTANCE_FROM_CRIMINAL = square_root $SUM_OF_DIFF_XY 
if
	0022:   150.0 > $PLAYERS_DISTANCE_FROM_CRIMINAL
then
	goto @CRIMINAL_IN_CAR
end

0086: $COP_TIME_LIMIT_FLOAT = $PLAYERS_DISTANCE_FROM_CRIMINAL 
0015: $COP_TIME_LIMIT_FLOAT /= 4.0 
0011: $COP_TIME_LIMIT_FLOAT *= 1000.0 
008C: $COP_TIME_LIMIT = float_to_integer $COP_TIME_LIMIT_FLOAT
if
	001A:   120000 > $COP_TIME_LIMIT
then
	0004: $COP_TIME_LIMIT = 120000
end


:GENERATE_CAR_MODEL
0209: $CAR_MODEL = random_int_in_ranges 90 140 //INC 90 NOT INC 140
if and
	0018:   $CAR_MODEL > 113 // CAR_BUGGY CAR_CORPSE CAR_POLICE CAR_ENFORCER CAR_SECURICAR CAR_BANSHEE BOAT_PREDATOR CAR_BUS
	001A:   128 > $CAR_MODEL // CAR_RHINO CAR_BARRACKS TRAIN_SUBWAY HELI_POLICE PLANE_DODO CAR_COACH
then
	goto @GENERATE_CAR_MODEL
end
if or
	0038:   $CAR_MODEL == CAR_FIRETRUCK
	0038:   $CAR_MODEL == CAR_AMBULANCE
	0038:   $CAR_MODEL == CAR_FBI
	0038:   $CAR_MODEL == CAR_RCBANDIT
	0038:   $CAR_MODEL == PLANE_AIRTRAIN
then
	goto @GENERATE_CAR_MODEL
end

0247: request_model $CAR_MODEL

while 8248:   not model $CAR_MODEL available
	wait 0 ms
	gosub @COPCAR_CANCELLED_CHECKS
end

0208: $CRIMINAL_HEADING = random_float 0.0 359.875 

while 8019:   not  17@ > 3000 
	wait 0 ms
	gosub @COPCAR_CANCELLED_CHECKS
end

00A5: create_car $CAR_MODEL at $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z store_to $CRIMINAL_CAR 
0428: set_car $CRIMINAL_CAR avoid_level_transitions 1 
0224: set_car $CRIMINAL_CAR health_to 800 
0249: release_model $CAR_MODEL 
0175: set_car $CRIMINAL_CAR z_angle_to $CRIMINAL_HEADING 
02C0: set $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z to_ped_path_coords_closest_to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
0376: $CRIMINAL = create_random_actor $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
0433: set_actor $CRIMINAL criminal_flag 1 
036A: put_actor $CRIMINAL in_car $CRIMINAL_CAR 
0319: set_actor $CRIMINAL running 1 
02A9: set_char_only_damaged_by_player $CRIMINAL to 1 
01ED: clear_actor $CRIMINAL threat_search 
0243: set_actor $CRIMINAL ped_stats_to PEDSTAT_TOUGH_GUY
011A: set_actor $CRIMINAL search_threat THREAT_PLAYER1
011A: set_actor $CRIMINAL search_threat THREAT_PLAYER2
011A: set_actor $CRIMINAL search_threat THREAT_PLAYER3
011A: set_actor $CRIMINAL search_threat THREAT_PLAYER4
011A: set_actor $CRIMINAL search_threat THREAT_CIVMALE
011A: set_actor $CRIMINAL search_threat THREAT_CIVFEMALE
011A: set_actor $CRIMINAL search_threat THREAT_COP
011A: set_actor $CRIMINAL search_threat THREAT_GANG_MAFIA
011A: set_actor $CRIMINAL search_threat THREAT_GANG_TRIAD
011A: set_actor $CRIMINAL search_threat THREAT_GANG_DIABLO
011A: set_actor $CRIMINAL search_threat THREAT_GANG_YAKUZA
011A: set_actor $CRIMINAL search_threat THREAT_GANG_YARDIE
011A: set_actor $CRIMINAL search_threat THREAT_GANG_COLOMBIAN
011A: set_actor $CRIMINAL search_threat THREAT_GANG_HOOD
011A: set_actor $CRIMINAL search_threat THREAT_EMERGENCY
011A: set_actor $CRIMINAL search_threat THREAT_PROSTITUTE
011A: set_actor $CRIMINAL search_threat THREAT_CRIMINAL
011A: set_actor $CRIMINAL search_threat THREAT_GUN
011A: set_actor $CRIMINAL search_threat THREAT_COP_CAR
011A: set_actor $CRIMINAL search_threat THREAT_FAST_CAR
011A: set_actor $CRIMINAL search_threat THREAT_FIREMAN
0291: set_actor $CRIMINAL attack_when_provoked 1 
01C3: remove_references_to_car $CRIMINAL_CAR 

if
	0038:   $FLAG_SHORESIDE_OPEN == 1 
then
	0209: $RANDOM_GUN = random_int_in_ranges 3 10
else
	if
		0038:   $FLAG_STAUNTON_OPEN == 0 
	then
		0209: $RANDOM_GUN = random_int_in_ranges 0 5
	else
		0209: $RANDOM_GUN = random_int_in_ranges 2 6 
	end
end

01B2: give_actor $CRIMINAL weapon $RANDOM_GUN ammo 1000 
0187: $CRIMINAL_BLIP = create_marker_above_actor $CRIMINAL 
03E6: remove_text_box 
if
	0038:   $GOT_SIREN_HELP_BEFORE == 0
then
	03E5: text_box 'SIREN_1'  // To turn on this vehicle's sirens tap the ~h~~k~~VEHICLE_HORN~ button~w~.
	0004: $GOT_SIREN_HELP_BEFORE = 1 
end

if
	0154:   actor $CRIMINAL in_zone 'PORT_W'  // Callahan Point
then
	0384: text_1string 'C_BREIF' 'PORT_W' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Callahan Point
end
if
	0154:   actor $CRIMINAL in_zone 'PORT_S'  // Atlantic Quays
then
	0384: text_1string 'C_BREIF' 'PORT_S' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Atlantic Quays
end
if
	0154:   actor $CRIMINAL in_zone 'PORT_E'  // Portland Harbor
then
	0384: text_1string 'C_BREIF' 'PORT_E' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Portland Harbor
end
if
	0154:   actor $CRIMINAL in_zone 'PORT_I'  // Trenton
then
	0384: text_1string 'C_BREIF' 'PORT_I' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Trenton
end
if
	0154:   actor $CRIMINAL in_zone 'S_VIEW'  // Portland View
then
	0384: text_1string 'C_BREIF' 'S_VIEW' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Portland View
end
if
	0154:   actor $CRIMINAL in_zone 'CHINA'  // Chinatown
then
	0384: text_1string 'C_BREIF' 'CHINA' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Chinatown
end
if
	0154:   actor $CRIMINAL in_zone 'EASTBAY'  // Portland Beach
then
	0384: text_1string 'C_BREIF' 'EASTBAY' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Portland Beach
end
if
	0154:   actor $CRIMINAL in_zone 'LITTLEI'  // Saint Mark's
then
	0384: text_1string 'C_BREIF' 'LITTLEI' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Saint Mark's
end
if
	0154:   actor $CRIMINAL in_zone 'REDLIGH'  // Red Light District
then
	0384: text_1string 'C_BREIF' 'REDLIGH' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Red Light District
end
if
	0154:   actor $CRIMINAL in_zone 'TOWERS'  // Hepburn Heights
then
	0384: text_1string 'C_BREIF' 'TOWERS' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Hepburn Heights
end
if
	0154:   actor $CRIMINAL in_zone 'HARWOOD'  // Harwood
then
	0384: text_1string 'C_BREIF' 'HARWOOD' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Harwood
end
if
	0154:   actor $CRIMINAL in_zone 'ROADBR1'  // Callahan Bridge
then
	0384: text_1string 'C_BREIF' 'ROADBR1' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Callahan Bridge
end
if
	0154:   actor $CRIMINAL in_zone 'ROADBR2'  // Callahan Bridge
then
	0384: text_1string 'C_BREIF' 'ROADBR2' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Callahan Bridge
end
if
	0154:   actor $CRIMINAL in_zone 'STADIUM'  // Aspatria
then
	0384: text_1string 'C_BREIF' 'STADIUM' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Aspatria
end
if
	0154:   actor $CRIMINAL in_zone 'HOSPI_2'  // Rockford
then
	0384: text_1string 'C_BREIF' 'HOSPI_2' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Rockford
end
if
	0154:   actor $CRIMINAL in_zone 'UNIVERS'  // Liberty Campus
then
	0384: text_1string 'C_BREIF' 'UNIVERS' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Liberty Campus
end
if
	0154:   actor $CRIMINAL in_zone 'CONSTRU'  // Fort Staunton
then
	0384: text_1string 'C_BREIF' 'CONSTRU' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Fort Staunton
end
if
	0154:   actor $CRIMINAL in_zone 'PARK'  // Belleville Park
then
	0384: text_1string 'C_BREIF' 'PARK' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Belleville Park
end
if
	0154:   actor $CRIMINAL in_zone 'COM_EAS'  // Newport
then
	0384: text_1string 'C_BREIF' 'COM_EAS' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Newport
end
if
	0154:   actor $CRIMINAL in_zone 'SHOPING'  // Bedford Point
then
	0384: text_1string 'C_BREIF' 'SHOPING' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Bedford Point
end
if
	0154:   actor $CRIMINAL in_zone 'YAKUSA'  // Torrington
then
	0384: text_1string 'C_BREIF' 'YAKUSA' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Torrington
end
if
	0154:   actor $CRIMINAL in_zone 'AIRPORT'  // Francis Intl. Airport
then
	0384: text_1string 'C_BREIF' 'AIRPORT' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Francis Intl. Airport
end
if
	0154:   actor $CRIMINAL in_zone 'PROJECT'  // Wichita Gardens
then
	0384: text_1string 'C_BREIF' 'PROJECT' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Wichita Gardens
end
if
	0154:   actor $CRIMINAL in_zone 'SUB_IND'  // Pike Creek
then
	0384: text_1string 'C_BREIF' 'SUB_IND' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Pike Creek
end
if
	0154:   actor $CRIMINAL in_zone 'SWANKS'  // Cedar Grove
then
	0384: text_1string 'C_BREIF' 'SWANKS' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Cedar Grove
end
if
	0154:   actor $CRIMINAL in_zone 'BIG_DAM'  // Cochrane Dam
then
	0384: text_1string 'C_BREIF' 'BIG_DAM' 5000 ms 1  // ~g~Suspect last seen in the ~a~ area. // Cochrane Dam
end

00A0: get_char_coordinates $CRIMINAL store_to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
03AA: play_suspect_last_seen_at $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
0006: 17@ = 0 
014E: start_timer_at $COP_TIME_LIMIT

while 8118:   not actor $CRIMINAL dead
	if
		001A:   1 > $COP_TIME_LIMIT 
	then
		if
			80E9:   not player $PLAYER_CHAR 0 $CRIMINAL radius 100.0 100.0
		then
			0164: disable_marker $CRIMINAL_BLIP 
			009B: delete_char $CRIMINAL 
			0004: $CRIMINAL_CREATED_FLAG = 0 
			00BC: print_now 'C_ESCP' time 3000 flag 1  // ~r~The suspect has escaped!
		else
			00BC: print_now 'C_TIME' time 3000 flag 1  // ~r~Your time as a law enforcer is over!
		end
		goto @COP_CAR_FAILED
	end
	gosub @COPCAR_CANCELLED_CHECKS
	if and
		0038:   $GOT_CAR_CRIM_IS_IN == 0 
		8184:   not actor $CRIMINAL health >= 70
	then
		0377: set_actor $CRIMINAL steal_any_car 
	end
	if and
		00DF:   is_char_in_any_car $CRIMINAL 
		0038:   $GOT_CAR_CRIM_IS_IN == 0
	then
		01C3: remove_references_to_car $CRIMINAL_CAR 
		00D9: store_car_char_is_in $CRIMINAL store_to $CRIMINAL_CAR 
		03ED: set_car $CRIMINAL_CAR not_damaged_when_upside_down 1 
		00AE: set_car_driving_style $CRIMINAL_CAR to DRIVINGMODE_AVOIDCARS
		00AD: set_car_cruise_speed $CRIMINAL_CAR to 42.0 
		00A8: car_wander_randomly $CRIMINAL_CAR 
		0428: set_car $CRIMINAL_CAR avoid_level_transitions 1 
		0004: $GOT_CAR_CRIM_IS_IN = 1 
	end
	if and
		0038:   $GOT_CAR_CRIM_IS_IN == 1
		80DF:   not is_char_in_any_car $CRIMINAL
	then
		0209: $RANGE_INT = random_int_in_ranges 0 5
		if
			0038:   $RANGE_INT == 0 
		then
			01CE: actor $CRIMINAL avoid_player $PLAYER_CHAR
		end
		if
			0038:   $RANGE_INT == 1
		then
			if
				00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 150.0 150.0 
			then
				0377: set_actor $CRIMINAL steal_any_car 
			else
				if
					0248:   model #SENTINEL available
				then
					00A0: get_char_coordinates $CRIMINAL store_to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
					03D3: point $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z get_nearby_vector $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z $WARP_HEADING_COP 
					01C3: remove_references_to_car $CRIMINAL_CAR 
					00A5: create_car #SENTINEL at $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z store_to $CRIMINAL_CAR 
					0175: set_car $CRIMINAL_CAR z_angle_to $WARP_HEADING_COP 
					01D5: actor $CRIMINAL go_to_and_drive_car $CRIMINAL_CAR
				else
					0377: set_actor $CRIMINAL steal_any_car
				end
			end
		end
		if
			0038:   $RANGE_INT == 2
		then			
			01CC: actor $CRIMINAL kill_player $PLAYER_CHAR
		end
		if
			0038:   $RANGE_INT == 3
		then
			if
				00E0:   is_player_in_any_car $PLAYER_CHAR
			then
				00DA: store_car_player_is_in $PLAYER_CHAR store_to $PLAYERS_COP_CAR 
				01D9: actor $CRIMINAL destroy_car $PLAYERS_COP_CAR
			else
				01CC: actor $CRIMINAL kill_player $PLAYER_CHAR 
			end
		end
		if
			0038:   $RANGE_INT == 4
		then
			0209: $RANGE_INT = random_int_in_ranges 0 8 
			009C: char_wander_dir $CRIMINAL to -1
		end
		0004: $GOT_CAR_CRIM_IS_IN = 0 
	end
	if and
		0038:   $GOT_CAR_CRIM_IS_IN == 1
		8119:   not car $CRIMINAL_CAR wrecked
	then
		if
			0019:   17@ > 1000
		then
			0006: 17@ = 0 
			if
				00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 20.0 20.0
			then
				00AD: set_car_cruise_speed $CRIMINAL_CAR to 46.0
			else
				if
					00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 50.0 50.0
				then
					00AD: set_car_cruise_speed $CRIMINAL_CAR to 39.0
				else
					if
						00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 90.0 90.0
					then
						00AD: set_car_cruise_speed $CRIMINAL_CAR to 32.0
					else
						if
							00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 130.0 130.0
						then
							00AD: set_car_cruise_speed $CRIMINAL_CAR to 26.0
						else
							00AD: set_car_cruise_speed $CRIMINAL_CAR to 18.0
						end
					end
				end
			end
		end
		if
			01C1:   car $CRIMINAL_CAR stopped
		then
			if
				0038:   $TIMER_RESET_FLAG == 0 
			then
				0006: 16@ = 0 
				0004: $TIMER_RESET_FLAG = 1
			end
			if and
				0019:   16@ > 8000 
				0038:   $TIMER_RESET_FLAG == 1
			then
				if
					00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 90.0 90.0 
				then
					01D3: actor $CRIMINAL leave_car $CRIMINAL_CAR 
					0004: $TIMER_RESET_FLAG = 0
				else
					if
						82CA:   not is_car_on_screen $CRIMINAL_CAR
					then
						00AA: get_car_coordinates $CRIMINAL_CAR store_to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
						03D3: point $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z get_nearby_vector $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z $WARP_HEADING_COP
						if
							80C2:   not is_point_on_screen $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 4.0 
						then
							00AB: set_car_coordinates $CRIMINAL_CAR to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
							0175: set_car $CRIMINAL_CAR z_angle_to $WARP_HEADING_COP 
							0004: $TIMER_RESET_FLAG = 0 
						end
					end
				end
			end
		end
		if and
			01F4:   car $CRIMINAL_CAR flipped 
			01C1:   car $CRIMINAL_CAR stopped
		then
			if
				00E9:   player $PLAYER_CHAR 0 $CRIMINAL radius 90.0 90.0
			then
				01D3: actor $CRIMINAL leave_car $CRIMINAL_CAR 
				03ED: set_car $CRIMINAL_CAR not_damaged_when_upside_down 0
			else
				if
					82CA:   not is_car_on_screen $CRIMINAL_CAR
				then
					00AA: get_car_coordinates $CRIMINAL_CAR store_to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
					03D3: point $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z get_nearby_vector $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z $WARP_HEADING_COP 
					if
						80C2:   not is_point_on_screen $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 4.0 
					then
						00AB: set_car_coordinates $CRIMINAL_CAR to $CRIMINAL_COORD_X $CRIMINAL_COORD_Y $CRIMINAL_COORD_Z 
						0175: set_car $CRIMINAL_CAR z_angle_to $WARP_HEADING_COP 
					end
				end
			end
		end
		if
			8185:   not car $CRIMINAL_CAR health >= 250 
		then
			01D3: actor $CRIMINAL leave_car $CRIMINAL_CAR
		end
	end
	wait 0 ms
end // while

01C2: remove_references_to_actor $CRIMINAL 
01C3: remove_references_to_car $CRIMINAL_CAR
0008: $TOTAL_CRIMINALS_KILLED += 1 
0402: increment_criminals_stopped 
018C: play_sound SOUND_PART_MISSION_COMPLETE at 0.0 0.0 0.0 
if
	0038:   $TOTAL_CRIMINALS_KILLED == 1 
then
	03C4: set_status_text_to $TOTAL_CRIMINALS_KILLED 0 'KILLS'  // KILLS:
end
0164: disable_marker $CRIMINAL_BLIP 
0084: $VIGILANTE_SCORE = $TOTAL_CRIMINALS_KILLED 
0010: $VIGILANTE_SCORE *= 500 
00BA: print_big 'C_PASS' time 5000 style 5  // THREAT ELIMINATED!
01E3: text_1number_styled 'REWARD' number $VIGILANTE_SCORE time 5000 style 6  // REWARD $~1~
0109: player $PLAYER_CHAR money += $VIGILANTE_SCORE 
if
	003A:   $TOTAL_CRIMINALS_KILLED == $VIGILANTE_BONUS_KILLS 
then
	0084: $VIGILANTE = $TOTAL_CRIMINALS_KILLED 
	0010: $VIGILANTE *= 2 
	0010: $VIGILANTE *= 500 
	0217: text_styled 'C_VIGIL' time 5000 style 5  // VIGILANTE BONUS!!
	0218: text_1number_styled 'REWARD' $VIGILANTE 6000 ms 6  // REWARD $~1~
	0109: player $PLAYER_CHAR money += $VIGILANTE 
	if
		0038:   $VIGILANTE_BONUS_KILLS == 10 
	then
		0413: enable $PLAYER_CHAR get_out_of_jail_free 1 
	end
	0008: $VIGILANTE_BONUS_KILLS += 5
end

if
	0038:   $LOCATION == 1 
then
	0008: $IND_COPCAR_KILLS += 1 
end
if
	0038:   $LOCATION == 2 
then
	0008: $COM_COPCAR_KILLS += 1 
end
if
	0038:   $LOCATION == 3 
then
	0008: $SUB_COPCAR_KILLS += 1 
end

if and
	0038:   $PLAY_PAGER_MESSAGE1 == 0 
	0038:   $IND_COPCAR_KILLS == 10
then
	014D: text_pager 'PAGEB12' 140 100 1  // Police Bribe delivered to hideout
	030C: progress_made = 1 
	0004: $PLAY_PAGER_MESSAGE1 = 1 
end
if and
	0038:   $PLAY_PAGER_MESSAGE1 == 1 
	0038:   $IND_COPCAR_KILLS == 20
then
	014D: text_pager 'PAGEB12' 140 100 1  // Police Bribe delivered to hideout
	030C: progress_made = 1 
	0004: $PLAY_PAGER_MESSAGE1 = 2
end
if and
	0038:   $PLAY_PAGER_MESSAGE2 == 0 
	0038:   $COM_COPCAR_KILLS == 10
then
	014D: text_pager 'PAGEB12' 140 100 1  // Police Bribe delivered to hideout
	030C: progress_made = 1 
	0004: $PLAY_PAGER_MESSAGE2 = 1 
end
if and
	0038:   $PLAY_PAGER_MESSAGE2 == 1 
	0038:   $COM_COPCAR_KILLS == 20
then
	014D: text_pager 'PAGEB12' 140 100 1  // Police Bribe delivered to hideout
	030C: progress_made = 1 
	0004: $PLAY_PAGER_MESSAGE2 = 2
end
if and
	0038:   $PLAY_PAGER_MESSAGE3 == 0 
	0038:   $SUB_COPCAR_KILLS == 10
then
	014D: text_pager 'PAGEB12' 140 100 1  // Police Bribe delivered to hideout
	030C: progress_made = 1 
	0004: $PLAY_PAGER_MESSAGE3 = 1 
end
if and
	0038:   $PLAY_PAGER_MESSAGE3 == 1 
	0038:   $SUB_COPCAR_KILLS == 20
then
	014D: text_pager 'PAGEB12' 140 100 1  // Police Bribe delivered to hideout
	030C: progress_made = 1 
	0004: $PLAY_PAGER_MESSAGE3 = 2
end

while true
	if and
		80DE:   not is_player_in_model $PLAYER_CHAR model #POLICE 
		80DE:   not is_player_in_model $PLAYER_CHAR model #ENFORCER 
		80DE:   not is_player_in_model $PLAYER_CHAR model #RHINO 
		80DE:   not is_player_in_model $PLAYER_CHAR model #FBICAR
	jf break
	if
		0038:   $GAME_TIME_FLAG == 0 
	then
		01BD: $GAME_TIMER_START = current_time_in_ms 
		if
			0018:   $COP_TIME_LIMIT > 60000 
		then
			0004: $COPCAR_TIMER = 60000 
		else
			0084: $COPCAR_TIMER = $COP_TIME_LIMIT 
		end
		0004: $GAME_TIME_FLAG = 1 
	end
	01BD: $GAME_TIME_PRESENT = current_time_in_ms 
	0084: $GAME_TIME_DIFFERENCE = $GAME_TIME_PRESENT 
	0060: $GAME_TIME_DIFFERENCE -= $GAME_TIMER_START 
	0060: $COPCAR_TIMER -= $GAME_TIME_DIFFERENCE 
	0084: $GAME_TIMER_START = $GAME_TIME_PRESENT 
	0084: $TIMER_IN_SECS = $COPCAR_TIMER 
	0014: $TIMER_IN_SECS /= 1000 
	01E5: text_1number_highpriority 'COPCART' $TIMER_IN_SECS flag 200 time 1  // ~g~You have ~1~ seconds to return to a police vehicle before the mission ends.
	if
		001A:   1 > $TIMER_IN_SECS 
	then
		00BC: print_now 'C_TIME' time 3000 flag 1  // ~r~Your time as a law enforcer is over!
		goto @COP_CAR_FAILED
	end
	wait 0 ms
end

if
	00E0:   is_player_in_any_car $PLAYER_CHAR 
then
	00DA: store_car_player_is_in $PLAYER_CHAR store_to $PLAYERS_COP_CAR 
	0227: $PLAYERS_COP_CAR_HEALTH = car $PLAYERS_COP_CAR health 
	0008: $PLAYERS_COP_CAR_HEALTH += 100 
	0224: set_car $PLAYERS_COP_CAR health_to $PLAYERS_COP_CAR_HEALTH
end

goto @COP_CAR_PASSED

///////////////////////////

:COP_CAR_PASSED
014F: stop_timer $COP_TIME_LIMIT 
0164: disable_marker $CRIMINAL_BLIP
if
	0038:   $CRIMINAL_CREATED_FLAG == 1 
then
	01C2: remove_references_to_actor $CRIMINAL 
	0004: $CRIMINAL_CREATED_FLAG = 0
end
goto @NEXT_COP_CAR

///////////////////////////

:COP_CAR_FAILED
00BA: print_big 'C_FAIL' time 5000 style 5  // Vigilante mission ended!
01E3: text_1number_styled 'C_KILLS' number $TOTAL_CRIMINALS_KILLED time 6000 style 6  // CRIMINALS KILLED: ~1~
014F: stop_timer $COP_TIME_LIMIT 
0151: remove_status_text $TOTAL_CRIMINALS_KILLED 
0164: disable_marker $CRIMINAL_BLIP 
03E6: remove_text_box 
0249: release_model #SENTINEL 
0249: release_model $CAR_MODEL 
if
	0038:   $CRIMINAL_CREATED_FLAG == 1 
then
	01C2: remove_references_to_actor $CRIMINAL 
	0004: $CRIMINAL_CREATED_FLAG = 0
end
0004: $ONMISSION = 0 
0004: $ON_VIGILANTE_MISSION = 0 
00D8: mission_has_finished 
goto @MISSION_END_COP_CAR

///////////////////////////

:COPCAR_CANCELLED_CHECKS
if and
	80DE:   not is_player_in_model $PLAYER_CHAR model #POLICE 
	80DE:   not is_player_in_model $PLAYER_CHAR model #ENFORCER 
	80DE:   not is_player_in_model $PLAYER_CHAR model #RHINO 
	80DE:   not is_player_in_model $PLAYER_CHAR model #FBICAR
then
	if
		0038:   $GAME_TIME_FLAG == 0 
	then
		01BD: $GAME_TIMER_START = current_time_in_ms
		if
			0018:   $COP_TIME_LIMIT > 60000
		then
			0004: $COPCAR_TIMER = 60000
		else
			0084: $COPCAR_TIMER = $COP_TIME_LIMIT 
		end
		0004: $GAME_TIME_FLAG = 1 
	end
	01BD: $GAME_TIME_PRESENT = current_time_in_ms 
	0084: $GAME_TIME_DIFFERENCE = $GAME_TIME_PRESENT 
	0060: $GAME_TIME_DIFFERENCE -= $GAME_TIMER_START 
	0060: $COPCAR_TIMER -= $GAME_TIME_DIFFERENCE 
	0084: $GAME_TIMER_START = $GAME_TIME_PRESENT 
	0084: $TIMER_IN_SECS = $COPCAR_TIMER 
	0014: $TIMER_IN_SECS /= 1000 
	01E5: text_1number_highpriority 'COPCART' $TIMER_IN_SECS flag 200 time 1  // ~g~You have ~1~ seconds to return to a police vehicle before the mission ends.
	if
		001A:   1 > $TIMER_IN_SECS
	then
		00BC: print_now 'C_TIME' time 3000 flag 1  // ~r~Your time as a law enforcer is over!
		goto @COP_CAR_FAILED
	end
end

0293: $CONTROLMODE = get_controller_mode 
if or
	00DE:   is_player_in_model $PLAYER_CHAR model #POLICE 
	00DE:   is_player_in_model $PLAYER_CHAR model #ENFORCER 
	00DE:   is_player_in_model $PLAYER_CHAR model #RHINO 
	00DE:   is_player_in_model $PLAYER_CHAR model #FBICAR
then
	if
		8038:   not  $CONTROLMODE == 3 
	then
		if
			00E1:   is_button_pressed PAD1 button RIGHTSHOCK
		then
			0004: $MISSION_END_BUTTON = 1 
		end
	else
		if
			00E1:   is_button_pressed PAD1 button SQUARE
		then
			0004: $MISSION_END_BUTTON = 1 
		end
	end
	if
		0038:   $MISSION_END_BUTTON == 1
	then
		if
			8038:   not  $CONTROLMODE == 3 
		then
			if
				80E1:   is_button_pressed PAD1 button RIGHTSHOCK
			then
				00BC: print_now 'C_CANC' time 3000 flag 1  // ~r~Vigilante mission cancelled!
				goto @COP_CAR_FAILED
			end
		else
			if
				80E1:   is_button_pressed PAD1 button SQUARE
			then
				00BC: print_now 'C_CANC' time 3000 flag 1  // ~r~Vigilante mission cancelled!
				goto @COP_CAR_FAILED
			end
		end
	end
end
return

///////////////////////////

:COP_CAR_CHECK_RANGE
return
