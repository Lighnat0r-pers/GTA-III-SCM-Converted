// *******************************************************************************************
// *******************************************************************************************
// *************************************CRRRRRAZY TAXI****************************************
// *******************************LETS MAKE SOME CRRRRRAZY MONEY!*****************************
// *******************************************************************************************
// *******************************************************************************************
// *******************************************************************************************

:M14_TAXI

// Mission start stuff

gosub @MISSION_START_TAXI1

:MISSION_END_TAXI1
gosub @MISSION_CLEANUP_TAXI1
end_thread

// ***************************************Mission Start*************************************

:MISSION_START_TAXI1
03A4: name_thread 'TAXI' 
0111: set_wasted_busted_check_to 0 // GSW - does deatharrest have to be switched off?  YES! well maybe...
0004: $ONMISSION = 1 
0004: $TAXI_COUNTDOWN_ALREADY_STARTED = 0 
0004: $TAXI_COUNTDOWN = 0 
0004: $TAXI_PASSED_THIS_SHOT = 0 
0004: $TAXI_SCORE = 0 
0004: $TAXI_PED1 = -1 
0004: $SPRAY_BLIP_ONSCREEN = 0 
0004: $TAXI_FUCKED_FLAG = 0 
0004: $IN_A_ROW_NUMBER = 5 
0004: $IN_A_ROW_CASH = 2000 
0001: wait 0 ms 
03C4: set_status_text_to $TAXI_PASSED_THIS_SHOT 0 'FARES'  // FARES:
if
	8256:   not is_player $PLAYER_CHAR defined
then
	goto @MISSION_TAXI1_FAILED
end
if
	00E0:   is_player_in_any_car $PLAYER_CHAR 
then
	00DA: store_car_player_is_in $PLAYER_CHAR store_to $TAXI_CAR1 
else
	goto @MISSION_TAXI1_FAILED
end
0216: set_car $TAXI_CAR1 taxi_available_light_to 1 
00BC: print_now 'TAXI1' time 1500 flag 1  // ~g~Look for a fare.
0001: wait 0 ms 

/////////////////////////////////

:START_TAXI_MISSION
if
	0038:   $DISPLAYED_TAXI_HELP_MESSAGE == 0 
then
	03E5: text_box 'TAXIH1'  // Stop near a highlighted pedestrian to pick them up then drive them to their destination before the time runs out.
	0004: $DISPLAYED_TAXI_HELP_MESSAGE = 1 
end
0004: $SCORE_FOR_THIS_FARE = 0
if
	8256:   not is_player $PLAYER_CHAR defined
then
	goto @MISSION_TAXI1_FAILED
end
if
	0119:   car $TAXI_CAR1 wrecked
then
	goto @MISSION_TAXI1_FAILED
end
if
	80DC:   not is_player_in_car $PLAYER_CHAR car $TAXI_CAR1
then
	goto @MISSION_TAXI1_FAILED
end

// // COMMENTED OUT BY ROCKSTAR
//if and
//	0038:    $TAXI_PASSED == 0
//	0121:   player $PLAYER_CHAR in_zone 'REDLIGH'  // Red Light District
//then
//	goto @SPECIAL_PED_MISSION1
//end
//

gosub @TAXI_FAIL_BUTTON_PRESSED
if and
	0038:   $TAXI_COUNTDOWN_ALREADY_STARTED == 1 
	0038:   $TAXI_COUNTDOWN == 0 
then
	goto @MISSION_TAXI1_FAILED
end

if
	03C6:   current_island == LEVEL_INDUSTRIAL
then
	02DD: get_random_actor $TAXI_PED1 in_zone 'IND_ZON'
end
if
	03C6:   current_island == LEVEL_COMMERCIAL
then
	02DD: get_random_actor $TAXI_PED1 in_zone 'COM_ZON'
end
if
	03C6:   current_island == LEVEL_SUBURBAN
then
	02DD: get_random_actor $TAXI_PED1 in_zone 'SUB_ZON'
end

if
	0038:   $TAXI_PED1 == -1 
then
	wait 0 ms
	goto @START_TAXI_MISSION
end

// START OF TAXI MISSION

009F: char_set_idle $TAXI_PED1 
01ED: clear_actor $TAXI_PED1 threat_search 
0291: set_actor $TAXI_PED1 attack_when_provoked 0 
0187: $BLIP1_CT1 = create_marker_above_actor $TAXI_PED1 
0365: set_actor $TAXI_PED1 objective_to -29

/////////////////////////////////

:PED_GET_IN_TAXI
while true
	if or
		80FD:   not player $PLAYER_CHAR 0 $TAXI_PED1 in_car radius 7.0 7.0 2.0 //IF CLOSE TO THE CHAR
		81C1:   not car $TAXI_CAR1 stopped
		// 80DC:   not is_player_in_car $PLAYER_CHAR car $TAXI_CAR1 // REMOVED BY R*
	jf break
	wait 0 ms
	gosub @CHECK_MISSION_STATUS_CHANGES
	if
		80FD:   not player $PLAYER_CHAR 0 $TAXI_PED1 in_car radius 90.0 90.0 20.0
	then
		goto @MISSION_TAXI1_PASSED
	end
	if
		8118:   not actor $TAXI_PED1 dead
	then
		020F: actor $TAXI_PED1 look_at_player $PLAYER_CHAR
	end
end //while

if
	8185:   not car $TAXI_CAR1 health >= 500
then
	00BC: print_now 'TAXI7' time 4000 flag 1  // ~r~Your car is trashed, get it repaired.
	if
		0038:   $SPRAY_BLIP_ONSCREEN == 0
	then
		0164: disable_marker $SPRAY_TAXI
		if
			03C6:   current_island == LEVEL_INDUSTRIAL
		then
			02A8: $SPRAY_TAXI = create_marker RADAR_SPRITE_SPRAY at 925.0 -359.5 -100.0
		end
		if
			03C6:   current_island == LEVEL_COMMERCIAL
		then
			02A8: $SPRAY_TAXI = create_marker RADAR_SPRITE_SPRAY at 379.0 -493.75 -100.0
		end
		if
			03C6:   current_island == LEVEL_SUBURBAN
		then
			02A8: $SPRAY_TAXI = create_marker RADAR_SPRITE_SPRAY at -1128.0 32.5 -100.0 
		end
		0004: $SPRAY_BLIP_ONSCREEN = 1 
		0004: $TAXI_FUCKED_FLAG = 1
	end
	goto @MISSION_TAXI1_PASSED
end

0319: set_actor $TAXI_PED1 running 1 
01D4: actor $TAXI_PED1 go_to_car $TAXI_CAR1 and_enter_it_as_a_passenger

while 80DB:   not is_char_in_car $TAXI_PED1 car $TAXI_CAR1
	wait 0 ms
	gosub @CHECK_MISSION_STATUS_CHANGES
	if
		80FD:   not player $PLAYER_CHAR 0 $TAXI_PED1 in_car radius 90.0 90.0 20.0
	then
		goto @MISSION_TAXI1_PASSED
	end
	if
		80FD:   not player $PLAYER_CHAR 0 $TAXI_PED1 in_car radius 7.0 7.0 2.0 
	then
		goto @PED_GET_IN_TAXI
	end
end //while

00A0: get_char_coordinates $TAXI_PED1 store_to $TAXI_PED_X $TAXI_PED_Y $TAXI_PED_Z 
0164: disable_marker $BLIP1_CT1 
0216: set_car $TAXI_CAR1 taxi_available_light_to 0 

/////////////////////////////////

:PASSENGER_DESTINATION
wait 0 ms
if
	8256:   not is_player $PLAYER_CHAR defined
then
	goto @MISSION_TAXI1_FAILED
end
if
	03C6:   current_island == LEVEL_INDUSTRIAL //INDUSTRIAL*******************************************
then
	0209: $CURRENT_TAXI_MISSION = random_int_in_ranges 1 11
	if
		8256:   not is_player $PLAYER_CHAR defined
	then
		goto @MISSION_TAXI1_FAILED
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 1
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'REDLIGH'  // Red Light District
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE1' time 5000 flag 1  // ~g~Destination ~w~'Meeouch Sex Kitten Club' ~g~in Redlight.
		0005: $TAXI_DESTX1 = 936.25 
		0005: $TAXI_DESTY1 = -462.5625 
		0005: $TAXI_DESTZ1 = 14.375 
		0005: $TAXI_DESTX2 = 913.6875 
		0005: $TAXI_DESTY2 = -456.0 
		0005: $TAXI_DESTZ2 = 16.375
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 2
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'S_VIEW'  // Portland View
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE2' time 5000 flag 1  // ~g~Destination ~w~'Supa Save' ~g~in Portland View.
		0005: $TAXI_DESTX1 = 1268.5 
		0005: $TAXI_DESTY1 = -616.375 
		0005: $TAXI_DESTZ1 = 11.6875 
		0005: $TAXI_DESTX2 = 1288.25 
		0005: $TAXI_DESTY2 = -627.5625 
		0005: $TAXI_DESTZ2 = 13.6875 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 3
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'CHINA'  // Chinatown
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE3' time 5000 flag 1  // ~g~Destination ~w~'old school hall' ~g~in Chinatown.
		0005: $TAXI_DESTX1 = 1008.875 
		0005: $TAXI_DESTY1 = -871.875 
		0005: $TAXI_DESTZ1 = 14.375 
		0005: $TAXI_DESTX2 = 995.25 
		0005: $TAXI_DESTY2 = -881.875 
		0005: $TAXI_DESTZ2 = 16.375 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 4
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'PORT_W'  // Callahan Point
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE4' time 5000 flag 1  // ~g~Destination ~w~'Greasy Joe's Cafe' ~g~in Callahan Point.
		0005: $TAXI_DESTX1 = 849.25 
		0005: $TAXI_DESTY1 = -990.0625 
		0005: $TAXI_DESTZ1 = 4.5 
		0005: $TAXI_DESTX2 = 869.375 
		0005: $TAXI_DESTY2 = -1002.563 
		0005: $TAXI_DESTZ2 = 6.5 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 5
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'REDLIGH'  // Red Light District
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE5' time 5000 flag 1  // ~g~Destination ~w~'AmmuNation' ~g~in Redlight.
		0005: $TAXI_DESTX1 = 1065.375 
		0005: $TAXI_DESTY1 = -394.0625 
		0005: $TAXI_DESTZ1 = 14.25 
		0005: $TAXI_DESTX2 = 1057.875 
		0005: $TAXI_DESTY2 = -408.875 
		0005: $TAXI_DESTZ2 = 16.25 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 6
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'LITTLEI'  // Saint Mark's
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE6' time 5000 flag 1  // ~g~Destination ~w~'Easy Credit Autos' ~g~in Saint Mark's.
		0005: $TAXI_DESTX1 = 1207.0 
		0005: $TAXI_DESTY1 = -122.0 
		0005: $TAXI_DESTZ1 = 14.0 
		0005: $TAXI_DESTX2 = 1224.0 
		0005: $TAXI_DESTY2 = -108.0 
		0005: $TAXI_DESTZ2 = 16.0 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 7
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'REDLIGH'  // Red Light District
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE7' time 5000 flag 1  // ~g~Destination ~w~'Woody's topless bar' ~g~in Redlight.
		0005: $TAXI_DESTX1 = 912.875 
		0005: $TAXI_DESTY1 = -419.0 
		0005: $TAXI_DESTZ1 = 14.0 
		0005: $TAXI_DESTX2 = 919.5 
		0005: $TAXI_DESTY2 = -401.25 
		0005: $TAXI_DESTZ2 = 16.0 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 8
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'LITTLEI'  // Saint Mark's
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE8' time 5000 flag 1  // ~g~Destination ~w~'Marcos Bistro' ~g~in Saint Mark's.
		0005: $TAXI_DESTX1 = 1345.75 
		0005: $TAXI_DESTY1 = -461.75 
		0005: $TAXI_DESTZ1 = 49.375 
		0005: $TAXI_DESTX2 = 1334.688 
		0005: $TAXI_DESTY2 = -447.0 
		0005: $TAXI_DESTZ2 = 51.375 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 9
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'PORT_E'  // Portland Harbor
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE9' time 5000 flag 1  // ~g~Destination ~w~'import export garage' ~g~in Portland Harbor.
		0005: $TAXI_DESTX1 = 1475.0 
		0005: $TAXI_DESTY1 = -686.0 
		0005: $TAXI_DESTZ1 = 11.25 
		0005: $TAXI_DESTX2 = 1485.25 
		0005: $TAXI_DESTY2 = -667.5 
		0005: $TAXI_DESTZ2 = 13.25
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 10
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'CHINA'  // Chinatown
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE10' time 5000 flag 1  // ~g~Destination ~w~'Punk Noodles' ~g~in Chinatown.
		0005: $TAXI_DESTX1 = 1039.063 
		0005: $TAXI_DESTY1 = -660.0625 
		0005: $TAXI_DESTZ1 = 14.375 
		0005: $TAXI_DESTX2 = 1043.875 
		0005: $TAXI_DESTY2 = -647.875 
		0005: $TAXI_DESTZ2 = 16.375
	end
end

if
	03C6:   current_island == LEVEL_COMMERCIAL //COMERCIAL*******************************************
then
	0209: $CURRENT_TAXI_MISSION = random_int_in_ranges 11 21
	if
		8256:   not is_player $PLAYER_CHAR defined
	then
		goto @MISSION_TAXI1_FAILED
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 11
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'CONSTRU'  // Fort Staunton
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE11' time 5000 flag 1  // ~g~Destination ~w~'Construction site' ~g~in Fort staunton.
		0005: $TAXI_DESTX1 = 441.8125 
		0005: $TAXI_DESTY1 = -193.0 
		0005: $TAXI_DESTZ1 = 20.3125 
		0005: $TAXI_DESTX2 = 447.375 
		0005: $TAXI_DESTY2 = -201.875 
		0005: $TAXI_DESTZ2 = 22.1875 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 12
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'STADIUM'  // Aspatria
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE12' time 5000 flag 1  // ~g~Destination ~w~'Football Stadium' ~g~in Aspatria.
		0005: $TAXI_DESTX1 = -27.0 
		0005: $TAXI_DESTY1 = -269.5625 
		0005: $TAXI_DESTZ1 = 14.9375 
		0005: $TAXI_DESTX2 = -11.6875 
		0005: $TAXI_DESTY2 = -278.875 
		0005: $TAXI_DESTZ2 = 16.875 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 13
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'SHOPING'  // Bedford Point
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE13' time 5000 flag 1  // ~g~Destination ~w~'The Church' ~g~in Bedford Point
		0005: $TAXI_DESTX1 = 22.0625 
		0005: $TAXI_DESTY1 = -1136.938 
		0005: $TAXI_DESTZ1 = 25.125 
		0005: $TAXI_DESTX2 = 28.9375 
		0005: $TAXI_DESTY2 = -1125.625 
		0005: $TAXI_DESTZ2 = 27.0625 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 14
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'YAKUSA'  // Torrington
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE14' time 5000 flag 1  // ~g~Destination ~w~'The Casino' ~g~in Torrington
		0005: $TAXI_DESTX1 = 421.375 
		0005: $TAXI_DESTY1 = -1390.813 
		0005: $TAXI_DESTZ1 = 24.9375 
		0005: $TAXI_DESTX2 = 415.4375 
		0005: $TAXI_DESTY2 = -1401.188 
		0005: $TAXI_DESTZ2 = 26.875
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 15
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'UNIVERS'  // Liberty Campus
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE15' time 5000 flag 1  // ~g~Destination ~w~'Liberty University' ~g~in Liberty Campus
		0005: $TAXI_DESTX1 = 183.125 
		0005: $TAXI_DESTY1 = -215.5 
		0005: $TAXI_DESTZ1 = 17.0 
		0005: $TAXI_DESTX2 = 167.1875 
		0005: $TAXI_DESTY2 = -221.1875 
		0005: $TAXI_DESTZ2 = 19.25 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 16
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'PARK'  // Belleville Park
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE16' time 5000 flag 1  // ~g~Destination ~w~'Shopping Mall' ~g~in Belleville Park Area
		0005: $TAXI_DESTX1 = 193.75 
		0005: $TAXI_DESTY1 = -626.1875 
		0005: $TAXI_DESTZ1 = 25.0625 
		0005: $TAXI_DESTX2 = 180.8125 
		0005: $TAXI_DESTY2 = -616.5 
		0005: $TAXI_DESTZ2 = 27.0625 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 17
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'COM_EAS'  // Newport
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE17' time 5000 flag 1  // ~g~Destination ~w~'Museum' ~g~in Newport
		0005: $TAXI_DESTX1 = 326.0625 
		0005: $TAXI_DESTY1 = -1001.688 
		0005: $TAXI_DESTZ1 = 29.0 
		0005: $TAXI_DESTX2 = 316.25 
		0005: $TAXI_DESTY2 = -1012.375 
		0005: $TAXI_DESTZ2 = 24.4375  
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 18
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'YAKUSA'  // Torrington
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE18' time 5000 flag 1  // ~g~Destination ~w~'AmCo Building' ~g~in Torrington
		0005: $TAXI_DESTX1 = 246.1875 
		0005: $TAXI_DESTY1 = -1192.75 
		0005: $TAXI_DESTZ1 = 24.6875 
		0005: $TAXI_DESTX2 = 256.625 
		0005: $TAXI_DESTY2 = -1184.125 
		0005: $TAXI_DESTZ2 = 26.6875
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 19
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'SHOPING'  // Bedford Point
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE19' time 5000 flag 1  // ~g~Destination ~w~'Bolt Burgers' ~g~in Bedford Point
		0005: $TAXI_DESTX1 = 28.6875 
		0005: $TAXI_DESTY1 = -1066.625 
		0005: $TAXI_DESTZ1 = 26.6875 
		0005: $TAXI_DESTX2 = 34.0625 
		0005: $TAXI_DESTY2 = -1078.938 
		0005: $TAXI_DESTZ2 = 24.9375
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 20
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'PARK'  // Belleville Park
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE20' time 5000 flag 1  // ~g~Destination ~w~'The Park' ~g~in Belleville
		0005: $TAXI_DESTX1 = 27.5 
		0005: $TAXI_DESTY1 = -776.375 
		0005: $TAXI_DESTZ1 = 26.25 
		0005: $TAXI_DESTX2 = 38.75 
		0005: $TAXI_DESTY2 = -765.0 
		0005: $TAXI_DESTZ2 = 28.5625 
	end
end

if
	03C6:   current_island == LEVEL_SUBURBAN //SUBURBIA*******************************************
then
	0209: $CURRENT_TAXI_MISSION = random_int_in_ranges 21 27
	if
		8256:   not is_player $PLAYER_CHAR defined
	then
		goto @MISSION_TAXI1_FAILED
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 21
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'AIRPORT'  // Francis Intl. Airport
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE21' time 5000 flag 1  // ~g~Destination ~w~'Francis intl. Airport'
		0005: $TAXI_DESTX1 = -744.5 
		0005: $TAXI_DESTY1 = -598.5625 
		0005: $TAXI_DESTZ1 = 8.0 
		0005: $TAXI_DESTX2 = -752.0 
		0005: $TAXI_DESTY2 = -617.1875 
		0005: $TAXI_DESTZ2 = 15.0  
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 22
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'BIG_DAM'  // Cochrane Dam
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE22' time 5000 flag 1  // ~g~Destination ~w~'Cochrane Dam'
		0005: $TAXI_DESTX1 = -1111.25 
		0005: $TAXI_DESTY1 = 522.875 
		0005: $TAXI_DESTZ1 = 65.0 
		0005: $TAXI_DESTX2 = -1101.0 
		0005: $TAXI_DESTY2 = 516.6875 
		0005: $TAXI_DESTZ2 = 70.0 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 23
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'SUB_IND'  // Pike Creek
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE23' time 5000 flag 1  // ~g~Destination ~w~'import export garage' ~g~in Cochrane Dam district
		0005: $TAXI_DESTX1 = -1107.875 
		0005: $TAXI_DESTY1 = 163.5 
		0005: $TAXI_DESTZ1 = 50.0 
		0005: $TAXI_DESTX2 = -1115.375 
		0005: $TAXI_DESTY2 = 155.5625 
		0005: $TAXI_DESTZ2 = 68.0 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 24
	then
		if
			0121:   player $PLAYER_CHAR in_zone 'SUB_IND'  // Pike Creek
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE24' time 5000 flag 1  // ~g~Destination ~w~'The hospital' ~g~in Pike Creek
		0005: $TAXI_DESTX1 = -1253.0 
		0005: $TAXI_DESTY1 = -136.5625 
		0005: $TAXI_DESTZ1 = 55.0 
		0005: $TAXI_DESTX2 = -1260.25 
		0005: $TAXI_DESTY2 = -148.375 
		0005: $TAXI_DESTZ2 = 62.0
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 25
	then
		if or
			0121:   player $PLAYER_CHAR in_zone 'SUB_ZO2'  // Shoreside Vale
			0121:   player $PLAYER_CHAR in_zone 'SUB_ZO3'  // Shoreside Vale
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE25' time 5000 flag 1  // ~g~Destination ~w~'The Park' ~g~in Shoreside Vale
		0005: $TAXI_DESTX1 = -230.0625 
		0005: $TAXI_DESTY1 = 311.5 
		0005: $TAXI_DESTZ1 = 0.0 
		0005: $TAXI_DESTX2 = -220.1875 
		0005: $TAXI_DESTY2 = 322.5 
		0005: $TAXI_DESTZ2 = 10.0 
	end
	if
		0038:   $CURRENT_TAXI_MISSION == 26
	then
		if or
			0121:   player $PLAYER_CHAR in_zone 'PROJECT'  // Wichita Gardens
			0121:   player $PLAYER_CHAR in_zone 'SWANKS'  // Cedar Grove
		then
			goto @PASSENGER_DESTINATION
		end
		00BC: print_now 'FARE26' time 5000 flag 1  // ~g~Destination ~w~'North West Towers' ~g~in Wichita Gardens
		0005: $TAXI_DESTX1 = -682.5 
		0005: $TAXI_DESTY1 = 95.25 
		0005: $TAXI_DESTZ1 = 10.0 
		0005: $TAXI_DESTX2 = -673.0 
		0005: $TAXI_DESTY2 = 88.375 
		0005: $TAXI_DESTZ2 = 25.0
	end
end

0086: $TAXI_BLIPX = $TAXI_DESTX1 
0059: $TAXI_BLIPX += $TAXI_DESTX2 
0015: $TAXI_BLIPX /= 2.0 
0086: $TAXI_BLIPY = $TAXI_DESTY1 
0059: $TAXI_BLIPY += $TAXI_DESTY2 
0015: $TAXI_BLIPY /= 2.0 
018A: $BLIP2_CT1 = create_checkpoint_at $TAXI_BLIPX $TAXI_BLIPY -100.0 
018B: show_on_radar $BLIP2_CT1 BLIP_ONLY
if
	0119:   car $TAXI_CAR1 wrecked
then
	goto @MISSION_TAXI1_FAILED
end
0086: $X_DIFF = $TAXI_PED_X 
0061: $X_DIFF -= $TAXI_BLIPX 
0086: $Y_DIFF = $TAXI_PED_Y 
0061: $Y_DIFF -= $TAXI_BLIPY 
0086: $X_DIFF_SQ = $X_DIFF 
0069: $X_DIFF_SQ *= $X_DIFF 
0086: $Y_DIFF_SQ = $Y_DIFF 
0069: $Y_DIFF_SQ *= $Y_DIFF 
0086: $TAXI_DISTANCE_SQ = $X_DIFF_SQ 
0059: $TAXI_DISTANCE_SQ += $Y_DIFF_SQ 
01FB: $TAXI_DISTANCE = square_root $TAXI_DISTANCE_SQ 
008C: $TAXI_DISTANCE_INT = float_to_integer $TAXI_DISTANCE 
0084: $TAXI_DISTANCE_INT_OLD = $TAXI_DISTANCE_INT 
if and
	03C6:   current_island == LEVEL_INDUSTRIAL
	0038:   $TAXI_PASSED_THIS_SHOT == 0
then
	0010: $TAXI_DISTANCE_INT *= 100
end
if and
	03C6:   current_island == LEVEL_COMMERCIAL
	0038:   $TAXI_PASSED_THIS_SHOT == 0
then
	0010: $TAXI_DISTANCE_INT *= 95
end
if and
	03C6:   current_island == LEVEL_SUBURBAN
	0038:   $TAXI_PASSED_THIS_SHOT == 0
then
	0010: $TAXI_DISTANCE_INT *= 115
end
if
	0038:   $TAXI_PASSED_THIS_SHOT == 1
then
	0010: $TAXI_DISTANCE_INT *= 90
end
if
	0038:   $TAXI_PASSED_THIS_SHOT == 2
then
	0010: $TAXI_DISTANCE_INT *= 85
end
if
	0038:   $TAXI_PASSED_THIS_SHOT == 3
then
	0010: $TAXI_DISTANCE_INT *= 84
end
if
	0038:   $TAXI_PASSED_THIS_SHOT == 4
then
	0010: $TAXI_DISTANCE_INT *= 83
end
if
	0038:   $TAXI_PASSED_THIS_SHOT == 5
then
	0010: $TAXI_DISTANCE_INT *= 82
end
if and
	0018:   $TAXI_PASSED_THIS_SHOT > 5 
	002A:   10 >= $TAXI_PASSED_THIS_SHOT
then
	0010: $TAXI_DISTANCE_INT *= 80
end
if and
	0018:   $TAXI_PASSED_THIS_SHOT > 11 // ERROR BY R*: SHOULD BE 10
	002A:   20 >= $TAXI_PASSED_THIS_SHOT
then
	0010: $TAXI_DISTANCE_INT *= 75
end
if and
	0018:   $TAXI_PASSED_THIS_SHOT > 20 
	002A:   50 >= $TAXI_PASSED_THIS_SHOT
then
	0010: $TAXI_DISTANCE_INT *= 70
end
if 
	0018:   $TAXI_PASSED_THIS_SHOT > 50
then
	0010: $TAXI_DISTANCE_INT *= 60
end

0058: $TAXI_COUNTDOWN += $TAXI_DISTANCE_INT 
0084: $SPEEDBONUS = $TAXI_DISTANCE_INT 
0014: $SPEEDBONUS /= 100 
0010: $SPEEDBONUS *= 65 
0006: 17@ = 0 
if
	0038:   $TAXI_COUNTDOWN_ALREADY_STARTED == 0
then
	014E: start_timer_at $TAXI_COUNTDOWN 
	0004: $TAXI_COUNTDOWN_ALREADY_STARTED = 1
end

if
	0038:   $TAXI_PASSED_THIS_SHOT == 0
then
	if
		03C6:   current_island == LEVEL_SUBURBAN
	then
		0008: $TAXI_COUNTDOWN += 15000
	else
		0008: $TAXI_COUNTDOWN += 10000
	end
end

while 81AC:   not car $TAXI_CAR1 stopped 1 $TAXI_DESTX1 $TAXI_DESTY1 $TAXI_DESTZ1 $TAXI_DESTX2 $TAXI_DESTY2 $TAXI_DESTZ2
	wait 0 ms
	if
		0038:   $TAXI_COUNTDOWN == 0 
	then
		goto @TAXI_OUT_OF_TIME
	end
	gosub @CHECK_MISSION_STATUS_CHANGES
	if
		8185:   not car $TAXI_CAR1 health >= 500
	then
		00BC: print_now 'TAXI7' time 4000 flag 1  // ~r~Your car is trashed, get it repaired.
		if
			0038:   $SPRAY_BLIP_ONSCREEN == 0
		then
			0164: disable_marker $SPRAY_TAXI
			if
				03C6:   current_island == LEVEL_INDUSTRIAL
			then
				02A8: $SPRAY_TAXI = create_marker RADAR_SPRITE_SPRAY at 925.0 -359.5 -100.0
			end
			if
				03C6:   current_island == LEVEL_COMMERCIAL
			then
				02A8: $SPRAY_TAXI = create_marker RADAR_SPRITE_SPRAY at 379.0 -493.75 -100.0
			end
			if
				03C6:   current_island == LEVEL_SUBURBAN
			then
				02A8: $SPRAY_TAXI = create_marker RADAR_SPRITE_SPRAY at -1128.0 32.5 -100.0 
			end
			0004: $SPRAY_BLIP_ONSCREEN = 1 
			gosub @TAXI_FUCKED
		end
		goto @MISSION_TAXI1_PASSED
	end
	if and
		01F4:   car $TAXI_CAR1 flipped 
		01C1:   car $TAXI_CAR1 stopped 
	then
		goto @TAXI_FUCKED
	end
end //while

/////////////////////////////////

:SCORE
if
	03C6:   current_island == 3
then
	if
		001F:   17@ > $SPEEDBONUS
	then
		0084: $SCORE_FOR_THIS_FARE = $TAXI_DISTANCE_INT_OLD 
		00BA: print_big 'TAXI4' time 5000 style 5  // Fare complete!
	else
		0084: $SCORE_FOR_THIS_FARE = $TAXI_DISTANCE_INT_OLD 
		0010: $SCORE_FOR_THIS_FARE *= 2 
		00BA: print_big 'TAXI5' time 5000 style 5  // SPEED BONUS!!
	end
else
	if
		001F:   17@ > $SPEEDBONUS
	then
		0084: $SCORE_FOR_THIS_FARE = $TAXI_DISTANCE_INT_OLD 
		0014: $SCORE_FOR_THIS_FARE /= 2 
		00BA: print_big 'TAXI4' time 5000 style 5  // Fare complete!
	else
		0084: $SCORE_FOR_THIS_FARE = $TAXI_DISTANCE_INT_OLD 
		00BA: print_big 'TAXI5' time 5000 style 5  // SPEED BONUS!!
	end
end

0109: player $PLAYER_CHAR money += $SCORE_FOR_THIS_FARE 
01E3: text_1number_styled 'TSCORE2' number $SCORE_FOR_THIS_FARE time 6000 style 6  // $~1~
018C: play_sound SOUND_PART_MISSION_COMPLETE at 0.0 0.0 0.0 
0058: $TAXI_SCORE += $SCORE_FOR_THIS_FARE 
0008: $TAXI_MISSION_DELIVERIES += 1 
0315: increment_taxi_dropoffs 
0008: $TAXI_PASSED_THIS_SHOT += 1 
//CREATE NEW TAXI AFTER COMPLETING 100 TAXI MISSIONS 
if and
	0038:   $NEW_TAXI_CREATED_BEFORE == 0 
	0038:   $TAXI_MISSION_DELIVERIES == 100
then
	014D: text_pager 'NEW_TAX' 140 2 0  // BIGGER! FASTER! HARDER! new Borgnine taxis open for business in Harwood. Call 555-BORGNINE today!
	014C: set_parked_car_generator $SWANK_TAXI cars_to_generate_to 101 
	030C: progress_made = 1 
	0004: $NEW_TAXI_CREATED_BEFORE = 1
end

0008: $TAXI_COUNTDOWN += 10000
gosub @MISSION_TAXI1_FAILED
if
	003A:   $TAXI_PASSED_THIS_SHOT == $IN_A_ROW_NUMBER
then
	036D: text_2numbers_styled 'IN_ROW' numbers $TAXI_PASSED_THIS_SHOT $IN_A_ROW_CASH time 5000 style 6  // ~1~ IN A ROW bonus! $~1~
	0109: player $PLAYER_CHAR money += $IN_A_ROW_CASH 
	0058: $TAXI_SCORE += $IN_A_ROW_CASH 
	0008: $IN_A_ROW_NUMBER += 5 
	0008: $IN_A_ROW_CASH += 2000
end
goto @MISSION_TAXI1_PASSED

/////////////////////////////////

//Taxi_fail_conditions

:TAXI_OUT_OF_TIME
00BC: print_now 'TAXI2' time 5000 flag 1  // ~r~You're out of time!
goto @MISSION_TAXI1_FAILED

/////////////////////////////////

:TAXI_FUCKED
wait 0 ms
00BC: print_now 'TAXI3' time 5000 flag 1  // ~r~Your passenger fled in terror!
if
	8118:   not actor $TAXI_PED1 dead
then
	0193: set_actor $TAXI_PED1 objective_to_act_like_ped
end
0004: $TAXI_FUCKED_FLAG = 1 
goto @MISSION_TAXI1_FAILED

/////////////////////////////////

:TAXI_FAIL_BUTTON_PRESSED
0293: $CONTROLMODE = get_controller_mode 
if
	8038:   not  $CONTROLMODE == 3
then
	if and
		00E1:   is_button_pressed PAD1 button RIGHTSHOCK 
		0038:   $ONMISSION == 1 
	then
		while 00E1:   is_button_pressed PAD1 button RIGHTSHOCK
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @MISSION_TAXI1_FAILED
			end
		end //while
		goto @MISSION_TAXI1_FAILED
	end
else
	if and
		00E1:   is_button_pressed PAD1 button SQUARE
		0038:   $ONMISSION == 1 
	then
		while 00E1:   is_button_pressed PAD1 button SQUARE
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @MISSION_TAXI1_FAILED
			end
		end //while
		goto @MISSION_TAXI1_FAILED
	end
end
return

/////////////////////////////////

:MISSION_TAXI1_PASSED
0164: disable_marker $BLIP1_CT1 
0164: disable_marker $BLIP2_CT1 
01C2: remove_references_to_actor $TAXI_PED1 
if
	8119:   not car $TAXI_CAR1 wrecked 
then
	0216: set_car $TAXI_CAR1 taxi_available_light_to 1 
end
wait 0 ms
00BD: print_soon 'TAXI1' time 1500 flag 1  // ~g~Look for a fare.
goto @START_TAXI_MISSION
return

/////////////////////////////////

:MISSION_TAXI1_FAILED
if and
	8118:   not actor $TAXI_PED1 dead
	8119:   not car $TAXI_CAR1 wrecked 
then
	if
		00DB:   is_char_in_car $TAXI_PED1 car $TAXI_CAR1
	then
		01D3: actor $TAXI_PED1 leave_car $TAXI_CAR1
		if
			0256:   is_player $PLAYER_CHAR defined
		then
			01B4: set_player $PLAYER_CHAR controllable 0
		end
		while 00DB:   is_char_in_car $TAXI_PED1 car $TAXI_CAR1 
			wait 0 ms
			if or
				0118:   actor $TAXI_PED1 dead 
				0119:   car $TAXI_CAR1 wrecked
				8256:   not is_player $PLAYER_CHAR defined
				80DC:   not is_player_in_car $PLAYER_CHAR car $TAXI_CAR1
			then
				goto @TAXI_PED_LEAVE2
			end
			0293: $CONTROLMODE = get_controller_mode
			if
				8038:   not  $CONTROLMODE == 3
			then
				if and
					00E1:   is_button_pressed PAD1 button RIGHTSHOCK
					0038:   $ONMISSION == 1 
				then
					goto @TAXI_PED_LEAVE2
				end
			else
				if and
					00E1:   is_button_pressed PAD1 button SQUARE
					0038:   $ONMISSION == 1 
				then
					goto @TAXI_PED_LEAVE2
				end
			end
		end //while
	end
end

if
	0256:   is_player $PLAYER_CHAR defined 
then
	01B4: set_player $PLAYER_CHAR controllable 1
end
if
	0038:   $TAXI_FUCKED_FLAG == 1
then
	goto @MISSION_END_TAXI1
end


/////////////////////////////////

:TAXI_PED_LEAVE2
wait 0 ms
if and
	8118:   not actor $TAXI_PED1 dead
	8119:   not car $TAXI_CAR1 wrecked 
then
	if
		00DB:   is_char_in_car $TAXI_PED1 car $TAXI_CAR1
	then
		009C: char_wander_dir $TAXI_PED1 to 0 
		01C2: remove_references_to_actor $TAXI_PED1
	end
end
if
	0256:   is_player $PLAYER_CHAR defined 
then
	01B4: set_player $PLAYER_CHAR controllable 1
end
goto @MISSION_END_TAXI1

/////////////////////////////////

// mission cleanup

:MISSION_CLEANUP_TAXI1
014F: stop_timer $TAXI_COUNTDOWN 
0151: remove_status_text $TAXI_PASSED_THIS_SHOT 
if
	8119:   not car $TAXI_CAR1 wrecked
then
	0216: set_car $TAXI_CAR1 taxi_available_light_to 0
end
0164: disable_marker $BLIP1_CT1 
0164: disable_marker $BLIP2_CT1 
0164: disable_marker $SPRAY_TAXI 
01C2: remove_references_to_actor $TAXI_PED1 
00BA: print_big 'TAXI6' time 5000 style 5  // Taxi mission over
01E3: text_1number_styled 'TSCORE' number $TAXI_SCORE time 6000 style 6  // EARNINGS: $~1~
0316: save_taxi_earnings_from $TAXI_SCORE 
0111: set_wasted_busted_check_to 1 
0004: $ONMISSION = 0 
0004: $ON_TAXI_MISSION = 0 
if
	0256:   is_player $PLAYER_CHAR defined 
then
	01B4: set_player $PLAYER_CHAR controllable 1
end
03E6: remove_text_box 
00D8: mission_has_finished 
return

/////////////////////////////////

:CHECK_MISSION_STATUS_CHANGES
if
	8256:   not is_player $PLAYER_CHAR defined
then
	goto @MISSION_TAXI1_FAILED
end
if
	0119:   car $TAXI_CAR1 wrecked
then
	goto @MISSION_TAXI1_FAILED
end
if
	0118:   actor $TAXI_PED1 dead 
then
	goto @MISSION_TAXI1_PASSED
end
if
	80DC:   not is_player_in_car $PLAYER_CHAR car $TAXI_CAR1
then
	goto @MISSION_TAXI1_FAILED
end
gosub @TAXI_FAIL_BUTTON_PRESSED
if and
	0038:   $TAXI_COUNTDOWN_ALREADY_STARTED == 1 
	0038:   $TAXI_COUNTDOWN == 0 
then
	goto @MISSION_TAXI1_FAILED
end
if and
	0185:   car $TAXI_CAR1 health >= 500 
	0038:   $SPRAY_BLIP_ONSCREEN == 1 
then
	0164: disable_marker $SPRAY_TAXI 
	0004: $SPRAY_BLIP_ONSCREEN = 0 
	0004: $TAXI_FUCKED_FLAG = 0
end
return
