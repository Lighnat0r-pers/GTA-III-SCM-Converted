// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***********************************ASUKA SUBURBAN MISSION 2****************************** 
// *****************************************************************************************
// **************************************'Espresso-2-Go'************************************
// *****************************************************************************************

// Mission start stuff

:M72_ESPRESSO2GO

gosub @MISSION_START_AS2
if 
	0112:   has_deatharrest_been_executed 
then
	gosub @MISSION_FAILED_AS2
end

:MISSION_END_AS2
gosub @MISSION_CLEANUP_AS2
end_thread


// ***************************************Mission Start*************************************

:MISSION_START_AS2
0317: increment_mission_attempts 
03A4: name_thread 'ASUSB2' 
0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_ASUKA2 = 1 
0001: wait 0 ms 

//---------------------------------SET FLAGS & VARIABLES-------------------------------------------

0004: $FLAG_KAPPA1_DEAD = 0 
0004: $FLAG_KAPPA2_DEAD = 0 
0004: $FLAG_KAPPA3_DEAD = 0 
0004: $FLAG_KAPPA4_DEAD = 0 
0004: $FLAG_KAPPA5_DEAD = 0 
0004: $FLAG_KAPPA6_DEAD = 0 
0004: $FLAG_KAPPA7_DEAD = 0 
0004: $FLAG_KAPPA8_DEAD = 0 
0004: $FLAG_KAPPA9_DEAD = 0 
0004: $FLAG_KAPPA10_DEAD = 0 
0004: $FLAG_KAPPA11_DEAD = 0 
0004: $FLAG_KAPPA12_DEAD = 0 
0004: $FLAG_KAPPA1_CREATED = 0 
0004: $FLAG_KAPPA2_CREATED = 0 
0004: $FLAG_KAPPA3_CREATED = 0 
0004: $FLAG_KAPPA4_CREATED = 0 
0004: $FLAG_KAPPA5_CREATED = 0 
0004: $FLAG_KAPPA6_CREATED = 0 
0004: $FLAG_KAPPA7_CREATED = 0 
0004: $FLAG_KAPPA8_CREATED = 0 
0004: $FLAG_KAPPA9_CREATED = 0 
0004: $FLAG_KAPPA10_CREATED = 0 
0004: $FLAG_KAPPA11_CREATED = 0 
0004: $FLAG_KAPPA12_CREATED = 0 
0004: $BLIP_KAPPA1_CREATED = 0 
0004: $BLIP_KAPPA2_CREATED = 0 
0004: $BLIP_KAPPA3_CREATED = 0 
0004: $BLIP_KAPPA4_CREATED = 0 
0004: $BLIP_KAPPA5_CREATED = 0 
0004: $BLIP_KAPPA6_CREATED = 0 
0004: $BLIP_KAPPA7_CREATED = 0 
0004: $BLIP_KAPPA8_CREATED = 0 
0004: $BLIP_KAPPA9_CREATED = 0 
0004: $BLIP_KAPPA10_CREATED = 0 
0004: $BLIP_KAPPA11_CREATED = 0 
0004: $BLIP_KAPPA12_CREATED = 0 
0004: $COUNTER_KAPPA_DEAD = 0 
0004: $PARTICLE_TIME_AS2 = 0 
0004: $COUNTER_KAPPA_DEAD_IND = 0 
0004: $COUNTER_KAPPA_DEAD_COM = 0 
0004: $COUNTER_KAPPA_DEAD_SUB = 0 
0004: $FLAG_AS2_1 = 0 
0004: $FLAG_AS2_2 = 0 
0004: $FLAG_AS2_3 = 0 
0004: $FLAG_PARTICLE_AS2 = 0 
0004: $FLAG_GUARD1_CREATED = 0 
0004: $KAPPA_TIME = 470000 
0004: $FLAG_TIMER_STARTED = 0 

// ****************************************LOCATION COORDS**************************************
0005: $KAPPA1_X = 1342.0 //dock entrance
0005: $KAPPA1_Y = -821.0 
0005: $KAPPA2_X = 1024.063 //tube station
0005: $KAPPA2_Y = -465.5625 
0005: $KAPPA3_X = 1351.188 //St Marks
0005: $KAPPA3_Y = -259.5 
0005: $KAPPA4_X = 286.375 //NEWPORT
0005: $KAPPA4_Y = -667.75 
0005: $KAPPA5_X = 28.0 //PARK
0005: $KAPPA5_Y = -850.0 
0005: $KAPPA6_X = 14.125 //BED POINT
0005: $KAPPA6_Y = -1140.0 
0005: $KAPPA7_X = 73.125 //BED POINT
0005: $KAPPA7_Y = -1359.75 
0005: $KAPPA8_X = -221.0 //ASPATRIA
0005: $KAPPA8_Y = -197.0 
0005: $KAPPA9_X = 282.75 // TORRINGTON
0005: $KAPPA9_Y = -1492.375 
0005: $KAPPA10_X = -644.0 // AIRPORT
0005: $KAPPA10_Y = -721.5 
0005: $KAPPA11_X = -212.0 //cedar grove
0005: $KAPPA11_Y = 310.0 
0005: $KAPPA12_X = -1255.0 //Pike Creek
0005: $KAPPA12_Y = -113.0 

// ****************************************START OF CUTSCENE********************************

0247: request_model #CSITECUTSCENE 
023C: load_special_actor 'ASUKA' as 1 
023C: load_special_actor 'MIGUEL' as 2 
02F3: load_object #CUTOBJ01 'PLAYERH' 
02F3: load_object #CUTOBJ02 'ASUKAH' 
02F3: load_object #CUTOBJ03 'WHIP' 
038B: load_all_models_now 


while true
	if or
		8248:   not model #CSITECUTSCENE available 
		823D:   not special_actor 1 loaded 
		823D:   not special_actor 2 loaded 
		8248:   not model #CUTOBJ01 available 
		8248:   not model #CUTOBJ02 available 
		8248:   not model #CUTOBJ03 available 	
	jf break
	wait 0 ms
end //while

02E4: load_cutscene_data 'A7_ETG' 
03AF: set_streaming 1 
0244: set_cutscene_pos 369.0 -327.5 18.4375 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
02E5: $CUTSCENE_ASUKA = create_cutscene_object #SPECIAL01 
02E6: set_cutscene_anim $CUTSCENE_ASUKA 'ASUKA' 
02E5: $CUTSCENE_MIGUEL = create_cutscene_object #SPECIAL02 
02E6: set_cutscene_anim $CUTSCENE_MIGUEL 'MIGUEL' 
02E5: $CS_WHIP = create_cutscene_object #CUTOBJ03 
02E6: set_cutscene_anim $CS_WHIP 'WHIP' 
02F4: create_cutscene_actor $PLAYER_CSHEAD from_head #CUTOBJ01 and_body $CUTSCENE_PLAYER 
02F5: set_head_anim $PLAYER_CSHEAD 'PLAYER' 
02F4: create_cutscene_actor $ASUKA_CSHEAD from_head #CUTOBJ02 and_body $CUTSCENE_ASUKA 
02F5: set_head_anim $ASUKA_CSHEAD 'ASUKA' 
0055: set_player_coordinates $PLAYER_CHAR to 373.75 -327.25 17.1875 
0171: set_player $PLAYER_CHAR z_angle_to 270.0 
016A: fade 1 for 250 ms 
03AD: set_rubbish 0 
03AF: set_streaming 0 
03B7: process_cut_scene_only 0 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime 


// Displays cutscene text

while 001A:   3445 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_A1' duration 10000 ms flag 1  // ~w~Miguel certainly has some of that famous Latin stamina.
while 001A:   7961 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_A2' duration 10000 ms flag 1  // ~w~I'm quite exhausted.
while 001A:   9836 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_A' duration 10000 ms flag 1  // ~w~We underestimated Catalina's plans for SPANK.
while 001A:   13013 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_B' duration 10000 ms flag 1  // ~w~It reaches far beyond the Yardies selling it on the street corners.
while 001A:   16802 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_C' duration 10000 ms flag 1  // ~w~The Cartel have a front company, The Kappa Coffee House.
while 001A:   20591 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_D' duration 10000 ms flag 1  // ~w~They've been selling SPANK through the street stalls.
while 001A:   23499 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_E' duration 10000 ms flag 1  // ~w~We have no choice but to put these drug stands out of operation.
while 001A:   27594 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'AS2_F' duration 10000 ms flag 1  // ~w~Smash them to splinters!!
while 001A:   30000 > $CUT_SCENE_TIME
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
016A: fade 0 for 0 ms 
0373: set_camera_directly_behind_player 
0001: wait 500 ms 
016A: fade 1 for 1500 ms 
0296: unload_special_actor 1 
0296: unload_special_actor 2 
0249: release_model #CSITECUTSCENE 
0249: release_model #CUTOBJ01 
0249: release_model #CUTOBJ02 
0249: release_model #CUTOBJ03 
03AD: set_rubbish 1 
03AF: set_streaming 1 
03B7: process_cut_scene_only 1 

// ******************************************END OF CUTSCENE********************************

03C4: set_status_text_to $COUNTER_KAPPA_DEAD COUNTER_DISPLAY_NUMBER 'KILLS'  // KILLS:
0006: 16@ = 0
00BC: print_now 'AS2_12' duration 5000 ms flag 1  // ~g~Cruise Liberty's districts to find ~b~Espresso-2-Go stalls!
00BD: print_soon 'AS2_12A' duration 5000 ms flag 1  // ~g~After you trash the first stall, you will have 8 minutes before the Cartel warn their pushers!
0237: set_gang GANG_MAFIA primary_weapon_to WEAPONTYPE_PISTOL secondary_weapon_to WEAPONTYPE_UZI 
0247: request_model #COFFEE 
0247: request_model #COLUMB 
0247: request_model #GANG11 
0247: request_model #GANG12 


while true
	if or
		8248:   not model #COFFEE available 
		8248:   not model #COLUMB available 
		8248:   not model #GANG11 available 
		8248:   not model #GANG12 available 
	jf break
	wait 0 ms
end //while

while 001A:   9 > $COUNTER_KAPPA_DEAD 
	wait 0 ms

	//-------------------------------------------CREATE KAPPAS------------------------------------------------
	if
		03C6:   current_island == LEVEL_INDUSTRIAL
	then
		if
			0038:   $FLAG_KAPPA1_CREATED == 0
		then
			if
				00E3:   player $PLAYER_CHAR 0 $KAPPA1_X $KAPPA1_Y radius 150.0 150.0 
			then
				009A: $KAPPA_CARTEL1 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG11 at $KAPPA1_X $KAPPA1_Y -100.0 
				011A: set_actor $KAPPA_CARTEL1 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL1 weapon WEAPONTYPE_UZI ammo 60 
				0350: set_actor $KAPPA_CARTEL1 maintain_position_when_attacked 1 
				0107: $KAPPA_1 = create_object #COFFEE at $KAPPA1_X $KAPPA1_Y -100.0 
				0177: set_object $KAPPA_1 z_angle_to 180.0 
				0382: set_object $KAPPA_1 collision_detection 1 
				0392: object $KAPPA_1 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA1_CREATED == 0 
				then
					0188: $BLIP_KAPPA1 = create_marker_above_object $KAPPA_1 
					0004: $BLIP_KAPPA1_CREATED = 1
				end
				0004: $FLAG_KAPPA1_CREATED = 1 
			end
		end
		if
			0038:   $FLAG_KAPPA3_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'LITTLEI'  // Saint Mark's
			then
				009A: $KAPPA_CARTEL3 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG11 at $KAPPA3_X $KAPPA3_Y -100.0
				0173: set_actor $KAPPA_CARTEL3 z_angle_to 270.0  
				011A: set_actor $KAPPA_CARTEL3 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL3 weapon WEAPONTYPE_SHOTGUN ammo 10 
				0350: set_actor $KAPPA_CARTEL3 maintain_position_when_attacked 1 
				0107: $KAPPA_3 = create_object #COFFEE at $KAPPA3_X $KAPPA3_Y -100.0 
				0177: set_object $KAPPA_3 z_angle_to 180.0 
				0382: set_object $KAPPA_3 collision_detection 1 
				0392: object $KAPPA_3 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA3_CREATED == 0 
				then
					0188: $BLIP_KAPPA3 = create_marker_above_object $KAPPA_3 
					0004: $BLIP_KAPPA3_CREATED = 1
				end
				0004: $FLAG_KAPPA3_CREATED = 1 
			end
		end
	end

	if
		03C6:   current_island == LEVEL_COMMERCIAL
	then
		if
			0038:   $FLAG_KAPPA4_CREATED == 0
		then
			009A: $KAPPA_CARTEL4 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG12 at $KAPPA4_X $KAPPA4_Y -100.0
			0173: set_actor $KAPPA_CARTEL4 z_angle_to 360.0  
			011A: set_actor $KAPPA_CARTEL4 search_threat THREAT_PLAYER1 
			01B2: give_actor $KAPPA_CARTEL4 weapon WEAPONTYPE_UZI ammo 60 
			0350: set_actor $KAPPA_CARTEL4 maintain_position_when_attacked 1 
			0107: $KAPPA_4 = create_object #COFFEE at $KAPPA4_X $KAPPA4_Y -100.0 
			0177: set_object $KAPPA_4 z_angle_to 270.0 
			0382: set_object $KAPPA_4 collision_detection 1 
			0392: object $KAPPA_4 toggle_in_moving_list 0 
			if
				0038:   $BLIP_KAPPA4_CREATED == 0 
			then
				0188: $BLIP_KAPPA4 = create_marker_above_object $KAPPA_4 
				0004: $BLIP_KAPPA4_CREATED = 1
			end
			0004: $FLAG_KAPPA4_CREATED = 1 
		end
		if
			0038:   $FLAG_KAPPA5_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'PARK'  // Belleville Park
			then
				009A: $KAPPA_CARTEL5 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG11 at $KAPPA5_X $KAPPA5_Y -100.0
				011A: set_actor $KAPPA_CARTEL5 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL5 weapon WEAPONTYPE_UZI ammo 60 
				0350: set_actor $KAPPA_CARTEL5 maintain_position_when_attacked 1 
				0107: $KAPPA_5 = create_object #COFFEE at $KAPPA5_X $KAPPA5_Y -100.0 
				0382: set_object $KAPPA_5 collision_detection 1 
				0392: object $KAPPA_5 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA5_CREATED == 0 
				then
					0188: $BLIP_KAPPA5 = create_marker_above_object $KAPPA_5 
					0004: $BLIP_KAPPA5_CREATED = 1
				end
				0004: $FLAG_KAPPA5_CREATED = 1 
			end
		end
		if
			0038:   $FLAG_KAPPA6_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'SHOPING'  // Bedford Point
			then
				009A: $KAPPA_CARTEL6 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG12 at $KAPPA6_X $KAPPA6_Y -100.0
				0173: set_actor $KAPPA_CARTEL6 z_angle_to 190.0 
				011A: set_actor $KAPPA_CARTEL6 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL6 weapon WEAPONTYPE_UZI ammo 60 
				0350: set_actor $KAPPA_CARTEL6 maintain_position_when_attacked 1 
				0107: $KAPPA_6 = create_object #COFFEE at $KAPPA6_X $KAPPA6_Y -100.0 
				0177: set_object $KAPPA_6 z_angle_to 145.0 
				0382: set_object $KAPPA_6 collision_detection 1 
				0392: object $KAPPA_6 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA6_CREATED == 0 
				then
					0188: $BLIP_KAPPA6 = create_marker_above_object $KAPPA_6 
					0004: $BLIP_KAPPA6_CREATED = 1
				end
				0004: $FLAG_KAPPA6_CREATED = 1 
			end
		end
		if
			0038:   $FLAG_KAPPA7_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'SHOPING'  // Bedford Point
			then
				009A: $KAPPA_CARTEL7 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG12 at $KAPPA7_X $KAPPA7_Y -100.0
				011A: set_actor $KAPPA_CARTEL7 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL7 weapon WEAPONTYPE_UZI ammo 60 
				0350: set_actor $KAPPA_CARTEL7 maintain_position_when_attacked 1 
				0107: $KAPPA_7 = create_object #COFFEE at $KAPPA7_X $KAPPA7_Y -100.0 
				0382: set_object $KAPPA_7 collision_detection 1 
				0392: object $KAPPA_7 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA7_CREATED == 0 
				then
					0188: $BLIP_KAPPA7 = create_marker_above_object $KAPPA_7 
					0004: $BLIP_KAPPA7_CREATED = 1
				end
				0004: $FLAG_KAPPA7_CREATED = 1 
			end
		end
		if
			0038:   $FLAG_KAPPA9_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'YAKUSA'  // Torrington
			then
				009A: $KAPPA_CARTEL9 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG12 at $KAPPA9_X $KAPPA9_Y -100.0
				011A: set_actor $KAPPA_CARTEL9 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL9 weapon WEAPONTYPE_SHOTGUN ammo 10 
				0350: set_actor $KAPPA_CARTEL9 maintain_position_when_attacked 1 
				0107: $KAPPA_9 = create_object #COFFEE at $KAPPA9_X $KAPPA9_Y -100.0 
				0177: set_object $KAPPA_9 z_angle_to 270.0 
				0382: set_object $KAPPA_9 collision_detection 1 
				0392: object $KAPPA_9 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA9_CREATED == 0 
				then
					0188: $BLIP_KAPPA9 = create_marker_above_object $KAPPA_9 
					0004: $BLIP_KAPPA9_CREATED = 1
				end
				0004: $FLAG_KAPPA9_CREATED = 1 
			end
		end
	end
	if
		03C6:   current_island == LEVEL_SUBURBAN
	then
		if
			0038:   $FLAG_KAPPA10_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'AIRPORT'  // Francis Intl. Airport
			then
				009A: $KAPPA_CARTEL10 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG12 at $KAPPA10_X $KAPPA10_Y -100.0
				0173: set_actor $KAPPA_CARTEL10 z_angle_to 50.0 
				011A: set_actor $KAPPA_CARTEL10 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL10 weapon WEAPONTYPE_UZI ammo 60 
				0350: set_actor $KAPPA_CARTEL10 maintain_position_when_attacked 1 
				0107: $KAPPA_10 = create_object #COFFEE at $KAPPA10_X $KAPPA10_Y -100.0 
				0382: set_object $KAPPA_10 collision_detection 1 
				0392: object $KAPPA_10 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA10_CREATED == 0 
				then
					0188: $BLIP_KAPPA10 = create_marker_above_object $KAPPA_10 
					0004: $BLIP_KAPPA10_CREATED = 1
				end
				0004: $FLAG_KAPPA10_CREATED = 1 
			end
		end
		if
			0038:   $FLAG_KAPPA12_CREATED == 0
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'SUB_IND'  // Pike Creek
			then
				009A: $KAPPA_CARTEL12 = create_char PEDTYPE_GANG_COLOMBIAN model #GANG12 at $KAPPA12_X $KAPPA12_Y -100.0
				011A: set_actor $KAPPA_CARTEL12 search_threat THREAT_PLAYER1 
				01B2: give_actor $KAPPA_CARTEL12 weapon WEAPONTYPE_UZI ammo 60 
				0350: set_actor $KAPPA_CARTEL12 maintain_position_when_attacked 1 
				0107: $KAPPA_12 = create_object #COFFEE at $KAPPA12_X $KAPPA12_Y -100.0 
				0382: set_object $KAPPA_12 collision_detection 1 
				0392: object $KAPPA_12 toggle_in_moving_list 0 
				if
					0038:   $BLIP_KAPPA12_CREATED == 0 
				then
					0188: $BLIP_KAPPA12 = create_marker_above_object $KAPPA_12 
					0004: $BLIP_KAPPA12_CREATED = 1
				end
				0004: $FLAG_KAPPA12_CREATED = 1 
			end
		end
		if or
			0038:   $FLAG_KAPPA10_CREATED == 1 
			0038:   $FLAG_KAPPA12_CREATED == 1 
		then
			if
				0038:   $FLAG_GUARD1_CREATED == 0 
			then
				00A5: $PATRIOT_1 = create_car #COLUMB at -706.25 -285.6875 18.25 
				0129: $VARMINT_1 = create_actor PEDTYPE_GANG_COLOMBIAN #GANG11 in_car $PATRIOT_1 driverseat 
				01B2: give_actor $VARMINT_1 weapon WEAPONTYPE_UZI ammo 60 
				01CC: actor $VARMINT_1 kill_player $PLAYER_CHAR 
				0004: $FLAG_GUARD1_CREATED = 1 
			end
		end
	end

	//------------------------------------------STEAM TIMER---------------------------------------------------

	if
		001F:   16@ > $PARTICLE_TIME_AS2 
	then
		0008: $PARTICLE_TIME_AS2 += 50 
		0004: $FLAG_PARTICLE_AS2 = 1 
	end

	//---------------------------------------KAPPA DEATH CHECK------------------------------------------------

	// Kappa 1
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA1_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA1_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_IND
	0084: $KAPPA_TEMP = $KAPPA_1
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA1
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL1
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA1_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_IND = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_1_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 3
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA3_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA3_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_IND
	0084: $KAPPA_TEMP = $KAPPA_3
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA3
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL3
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA3_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_IND = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_3_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 4
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA4_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA4_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_COM
	0084: $KAPPA_TEMP = $KAPPA_4
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA4
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL4
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA4_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_COM = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_4_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 5
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA5_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA5_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_COM
	0084: $KAPPA_TEMP = $KAPPA_5
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA5
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL5
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA5_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_COM = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_5_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 6
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA6_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA6_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_COM
	0084: $KAPPA_TEMP = $KAPPA_6
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA6
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL6
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA6_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_COM = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_6_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 7
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA7_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA7_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_COM
	0084: $KAPPA_TEMP = $KAPPA_7
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA7
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL7
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA7_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_COM = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_7_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 9
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA9_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA9_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_COM
	0084: $KAPPA_TEMP = $KAPPA_9
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA9
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL9
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA9_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_COM = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_9_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 10
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA10_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA10_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_SUB
	0084: $KAPPA_TEMP = $KAPPA_10
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA10
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL10
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA10_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_SUB = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_10_FIRE = $KAPPA_TEMP_FIRE

	// Kappa 12
	0084: $FLAG_KAPPA_TEMP_DEAD = $FLAG_KAPPA12_DEAD
	0084: $FLAG_KAPPA_TEMP_CREATED = $FLAG_KAPPA12_CREATED
	0084: $COUNTER_KAPPA_CUR_ISL_DEAD = $COUNTER_KAPPA_DEAD_SUB
	0084: $KAPPA_TEMP = $KAPPA_12
	0084: $BLIP_KAPPA_TEMP = $BLIP_KAPPA12
	0084: $KAPPA_CARTEL_TEMP = $KAPPA_CARTEL12
	gosub @KAPPA_DEATH_CHECK
	0084: $FLAG_KAPPA12_DEAD = $FLAG_KAPPA_TEMP_DEAD
	0084: $COUNTER_KAPPA_DEAD_SUB = $COUNTER_KAPPA_CUR_ISL_DEAD
	0084: $KAPPA_12_FIRE = $KAPPA_TEMP_FIRE


	// Reset steam timer
	0004: $FLAG_PARTICLE_AS2 = 0 

	//-------------------------------------------TIMER-------TIMER----TIMER------------------------------------
	if
		001A:   1 > $KAPPA_TIME
	then
		00BC: print_now 'AS2_4' duration 3000 ms flag 1  // ~r~The Cartel have warned their pushers!!
		goto @MISSION_FAILED_AS2
	end

	if and
		0038:   $COUNTER_KAPPA_DEAD == 1 
		0038:   $FLAG_TIMER_STARTED == 0
	then
		014E: start_timer_at $KAPPA_TIME 
		0004: $FLAG_TIMER_STARTED = 1 
	end

	//----------------------------------------PLAYER INFO------------------------------------------------------

	if and
		0038:   $COUNTER_KAPPA_DEAD_IND == 2 
		0038:   $FLAG_AS2_1 == 0 
	then
		00BC: print_now 'AS2_1' duration 3000 ms flag 1  // ~g~All espresso stalls in Portland wrecked!!
		0001: wait 3000 ms 
		0004: $FLAG_AS2_1 = 1
		if and
			0038:   $FLAG_AS2_2 == 0 
			0038:   $FLAG_AS2_3 == 0 
		then
			00BC: print_now 'AS2_5' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Shoreside Vale and on Staunton Island!
			0001: wait 3000 ms 
		else
			if
				0038:   $FLAG_AS2_3 == 0
			then
				00BC: print_now 'AS2_6' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Shoreside Vale!
				0001: wait 3000 ms 
			else
				if
					0038:   $FLAG_AS2_2 == 0 
				then
					00BC: print_now 'AS2_7' duration 4000 ms flag 1  // ~g~There are still espresso stalls on Staunton Island!
					0001: wait 3000 ms 
				end
			end
		end
	end

	if and
		0038:   $COUNTER_KAPPA_DEAD_COM == 5 
		0038:   $FLAG_AS2_2 == 0 
	then
		00BC: print_now 'AS2_2' duration 3000 ms flag 1  // ~g~All espresso stalls in Staunton Island wrecked!!
		0001: wait 3000 ms 
		0004: $FLAG_AS2_2 = 1
		if and
			0038:   $FLAG_AS2_1 == 0 
			0038:   $FLAG_AS2_3 == 0 
		then
			00BC: print_now 'AS2_9' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Portland and Shoreside Vale!
			0001: wait 3000 ms 
		else
			if
				0038:   $FLAG_AS2_3 == 0
			then
				00BC: print_now 'AS2_6' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Shoreside Vale!
				0001: wait 3000 ms 
			else
				if
					0038:   $FLAG_AS2_1 == 0 
				then
					00BC: print_now 'AS2_8' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Portland!
					0001: wait 3000 ms 
				end
			end
		end
	end

	if and
		0038:   $COUNTER_KAPPA_DEAD_SUB == 2 
		0038:   $FLAG_AS2_3 == 0 
	then
		00BC: print_now 'AS2_3' duration 3000 ms flag 1  // ~g~All espresso stalls in Shoreside Vale wrecked!!
		0001: wait 3000 ms 
		0004: $FLAG_AS2_3 = 1
		if and
			0038:   $FLAG_AS2_1 == 0 
			0038:   $FLAG_AS2_2 == 0 
		then
			00BC: print_now 'AS2_10' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Portland and on Staunton Island
			0001: wait 3000 ms 
		else
			if
				0038:   $FLAG_AS2_2 == 0
			then
				00BC: print_now 'AS2_7' duration 4000 ms flag 1  // ~g~There are still espresso stalls on Staunton Island!
				0001: wait 3000 ms 
			else
				if
					0038:   $FLAG_AS2_1 == 0 
				then
					00BC: print_now 'AS2_8' duration 4000 ms flag 1  // ~g~There are still espresso stalls in Portland!
					0001: wait 3000 ms 
				end
			end
		end
	end

end //while


goto @MISSION_PASSED_AS2


/////////////////////////////////////////

:KAPPA_DEATH_CHECK
if and
	0038:   $FLAG_KAPPA_TEMP_DEAD == 0 
	0038:   $FLAG_KAPPA_TEMP_CREATED == 1 
then
	if
		0366:    had_object_been_damaged $KAPPA_TEMP 
	then
		0004: $FLAG_KAPPA_TEMP_DEAD = 1 
		0008: $COUNTER_KAPPA_DEAD += 1 
		0008: $COUNTER_KAPPA_CUR_ISL_DEAD += 1 
		01E3: text_1number_styled 'AS2_11' number $COUNTER_KAPPA_DEAD duration 2000 ms style 1  // ~g~~1~ OF 9!
		0164: disable_marker $BLIP_KAPPA_TEMP 
		if
			8118:   not actor $KAPPA_CARTEL_TEMP dead 
		then
			01ED: clear_actor $KAPPA_CARTEL_TEMP threat_search 
			0239: actor $KAPPA_CARTEL_TEMP run_to 1335.5 -820.5 
		end
	end
	if
		8118:   not actor $KAPPA_CARTEL_TEMP dead
	then
		020F: actor $KAPPA_CARTEL_TEMP look_at_player $PLAYER_CHAR 
	end
	if
		0038:   $FLAG_PARTICLE_AS2 == 1 
	then
		01BB: store_object $KAPPA_TEMP position_to $STEAM_X $STEAM_Y $STEAM_Z 
		000D: $STEAM_Y -= 1.0 
		039D: scatter_particles type POBJECT_DRY_ICE_SLOWMOTION 0.25 0 0 0 50 from $STEAM_X $STEAM_Y $STEAM_Z to 0.0 0.0 0.0 
	end
end
if
	0018:   $FLAG_KAPPA_TEMP_DEAD > 0 
then
	if
		8118:   not actor $KAPPA_CARTEL_TEMP dead 
	then
		if
			0126:   actor $KAPPA_CARTEL_TEMP walking 
		then
			020F: actor $KAPPA_CARTEL_TEMP look_at_player $PLAYER_CHAR 
			0350: set_actor $KAPPA_CARTEL_TEMP maintain_position_when_attacked 0 
			011A: set_actor $KAPPA_CARTEL_TEMP search_threat THREAT_PLAYER1 
		end
	end
end
if and
	0038:   $FLAG_PARTICLE_AS2 == 1 
	0038:   $FLAG_KAPPA_TEMP_DEAD == 1 
	0038:   $FLAG_KAPPA_TEMP_CREATED == 1 
then
	01BB: store_object $KAPPA_TEMP position_to $STEAM_X $STEAM_Y $STEAM_Z 
	000D: $STEAM_Y -= 1.0 
	02CF: $KAPPA_TEMP_FIRE = create_fire_at $STEAM_X $STEAM_Y $STEAM_Z 
	0004: $FLAG_KAPPA_TEMP_DEAD = 2 
end
return


/////////////////////////////////////////

// Mission Failed

:MISSION_FAILED_AS2
00BA: print_big 'M_FAIL' duration 2000 ms style 1  // MISSION FAILED!
if
	0130:   player $PLAYER_CHAR busted 
then
	0420: override_police_station LEVEL_COMMERCIAL 
end
if
	0117:   player $PLAYER_CHAR wasted 
then
	041F: override_hospital LEVEL_COMMERCIAL 
end
goto @MISSION_END_AS2

/////////////////////////////////////////

// Mission Passed

:MISSION_PASSED_AS2
0004: $ESPRESSO_2_GO_COMPLETED = 1 
01E3: text_1number_styled 'M_PASS' number 40000 duration 5000 ms style 1  // MISSION PASSED! $~1~
0394: play_mission_passed_music 1 
0110: clear_player $PLAYER_CHAR wanted_level 
0109: player $PLAYER_CHAR money += 40000 
0318: set_latest_mission_passed 'AS2'  // 'ESPRESSO-2-GO!'
030C: set_mission_points += 1 
004F: create_thread @ASUKA_SUBURBAN_MISSION3_LOOP

goto @MISSION_END_AS2

/////////////////////////////////////////

// Mission Cleanup

:MISSION_CLEANUP_AS2
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_ASUKA2 = 0 
014F: stop_timer $KAPPA_TIME 
0151: remove_status_text $COUNTER_KAPPA_DEAD 
0237: set_gang GANG_MAFIA primary_weapon_to WEAPONTYPE_PISTOL secondary_weapon_to WEAPONTYPE_SHOTGUN  
if and
	0038:   $FLAG_KAPPA1_DEAD == 0 
	0038:   $FLAG_KAPPA1_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA1
end
if and
	0038:   $FLAG_KAPPA3_DEAD == 0 
	0038:   $FLAG_KAPPA3_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA3
end
if and
	0038:   $FLAG_KAPPA4_DEAD == 0 
	0038:   $FLAG_KAPPA4_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA4
end
if and
	0038:   $FLAG_KAPPA5_DEAD == 0 
	0038:   $FLAG_KAPPA5_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA5
end
if and
	0038:   $FLAG_KAPPA6_DEAD == 0 
	0038:   $FLAG_KAPPA6_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA6
end
if and
	0038:   $FLAG_KAPPA7_DEAD == 0 
	0038:   $FLAG_KAPPA7_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA7
end
if and
	0038:   $FLAG_KAPPA9_DEAD == 0 
	0038:   $FLAG_KAPPA9_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA9
end
if and
	0038:   $FLAG_KAPPA10_DEAD == 0 
	0038:   $FLAG_KAPPA10_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA10
end
if and
	0038:   $FLAG_KAPPA12_DEAD == 0 
	0038:   $FLAG_KAPPA12_CREATED == 1 
then
	0164: disable_marker $BLIP_KAPPA12
end
031A: remove_all_fires 
01C4: remove_references_to_object $KAPPA_1 
01C4: remove_references_to_object $KAPPA_3 
01C4: remove_references_to_object $KAPPA_4 
01C4: remove_references_to_object $KAPPA_5 
01C4: remove_references_to_object $KAPPA_6 
01C4: remove_references_to_object $KAPPA_7 
01C4: remove_references_to_object $KAPPA_9 
01C4: remove_references_to_object $KAPPA_10 
01C4: remove_references_to_object $KAPPA_12 
0249: release_model #COLUMB 
0249: release_model #COFFEE 
0249: release_model #GANG11 
0249: release_model #GANG12 
00D8: mission_has_finished 
return

////////////////////////////////////////

