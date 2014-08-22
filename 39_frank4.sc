// *****************************************************************************************
// *****************************************************************************************
// *************************************Frankie Mission 3***********************************
// ***************************************"BOMB DA BASE"************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

:M39_LASTREQUESTS

gosub @MISSION_START_FRANK4
if 
	0112:   has_deatharrest_been_executed 
then
	gosub @MISSION_FAILED_FRANK4
end

:MISSION_END_FRANK4
gosub @MISSION_CLEANUP_FRANK4
end_thread


// ***************************************Mission Start*************************************

:MISSION_START_FRANK4

0317: increment_mission_attempts 
0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_FRANK = 1 
03A4: name_thread 'FRANK4' 
0001: wait 0 ms 
0004: $GIRLS_ATTACK_PLAYER = 0 
0004: $CAR_DEAD_ONCE = 0 
023C: load_special_actor 'FRANKIE' as 1 
02F3: load_object #CUTOBJ01 'PLAYERH' 
02F3: load_object #CUTOBJ02 'FRANKH' 
0247: request_model #GANG02 
0247: request_model #FRANKSCLB02 
0247: request_model #SALVSDETAIL 
0247: request_model #SWANK_INSIDE 
038B: load_all_models_now 

while true
	if or
		823D:   not special_actor 1 loaded 
		8248:   not model #CUTOBJ01 available 
		8248:   not model #CUTOBJ02 available 
		8248:   not model #GANG02 available
		8248:   not model #FRANKSCLB02 available 
		8248:   not model #SALVSDETAIL available 
		8248:   not model #SWANK_INSIDE available
	jf break
	wait 0 ms
end //while

02E4: load_cutscene_data 'S5_LRQ' 
0244: set_cutscene_pos 1457.75 -185.3125 54.875 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
02F4: create_cutscene_actor $PLAYER_CSHEAD from_head #CUTOBJ01 and_body $CUTSCENE_PLAYER 
02F5: set_head_anim $PLAYER_CSHEAD 'PLAYER' 
02E5: $CUTSCENE_SALVATORE = create_cutscene_object #SPECIAL01 
02E6: set_cutscene_anim $CUTSCENE_SALVATORE 'FRANKIE' 
02F4: create_cutscene_actor $SALVATORE_CSHEAD from_head #CUTOBJ02 and_body $CUTSCENE_SALVATORE 
02F5: set_head_anim $SALVATORE_CSHEAD 'FRANK' 
02E5: $CUTSCENE_MAFIA = create_cutscene_object #GANG02 
02E6: set_cutscene_anim $CUTSCENE_MAFIA 'GANG02' 
0055: set_player_coordinates $PLAYER_CHAR to 1455.188 -186.75 55.0 
0171: set_player $PLAYER_CHAR z_angle_to 130.0 
016A: fade 1 for 1500 ms 
03AD: set_rubbish 0 
03AF: set_streaming 1 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime 

// Displays cutscene text

while 001A:   2397 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_A' duration 10000 ms flag 1  // ~w~It's my favorite cleaner.
while 001A:   5260 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_B' duration 10000 ms flag 1  // ~w~I'm proud of you my boy, you kicked the shit out of those grease balls.
while 001A:   9273 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_C' duration 10000 ms flag 1  // ~w~I've got just one little job for you before we can all celebrate.
while 001A:   12861 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_D' duration 10000 ms flag 1  // ~w~There's a car around the block from Luigi's club.
while 001A:   15447 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_E' duration 10000 ms flag 1  // ~w~The inside is covered in brains.
while 001A:   18080 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_F' duration 10000 ms flag 1  // ~w~We had to help some guy make up his mind and it proved a little messy.
while 001A:   22832 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_H' duration 10000 ms flag 1  // ~w~Take it to the crusher before the cops find it.
while 001A:   24908 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BE: clear_prints 
while 001A:   26333 > $CUT_SCENE_TIME
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

03AD: set_rubbish 1 
02EA: end_cutscene 
0373: set_camera_directly_behind_player 
0001: wait 500 ms 
016A: fade 1 for 1000 ms 
0296: unload_special_actor 1 
0249: release_model #CUTOBJ01 
0249: release_model #CUTOBJ02 
0249: release_model #GANG02 
0249: release_model #FRANKSCLB02 
0249: release_model #SALVSDETAIL 
0249: release_model #SWANK_INSIDE 
0247: request_model #REEFER 
0247: request_model #CHEETAH 

while true
	if or
		8248:   not model #REEFER available 
		8248:   not model #CHEETAH available
	jf break
	wait 0 ms
end //while

if
	0038:   $BARRIERS_BEEN_ADDED == 0 
then
	029B: $SUBWAY_GATE2 = init_object #SUBWAYGATE at -672.0 -760.0 8.25 
	01C7: remove_object_from_mission_cleanup_list $SUBWAY_GATE2 
	0177: set_object $SUBWAY_GATE2 z_angle_to 270.0 
	029B: $SUBWAY_GATE3 = init_object #SUBWAYGATE at -672.0 -768.875 8.25 
	01C7: remove_object_from_mission_cleanup_list $SUBWAY_GATE3 
	0177: set_object $SUBWAY_GATE3 z_angle_to 270.0 
	029B: $TUNNEL_STAUNTON_BLOCK = init_object #TUNNELENTRANCE at 533.25 97.0 -21.25 
	01C7: remove_object_from_mission_cleanup_list $TUNNEL_STAUNTON_BLOCK 
	0177: set_object $TUNNEL_STAUNTON_BLOCK z_angle_to 45.0 
	0004: $BARRIERS_BEEN_ADDED = 1 
end

00A5: $BOMB_CAR = create_car #CHEETAH at 951.0 -421.0 14.5625
0242: set_car $BOMB_CAR bomb_status_to CARBOMB_ONIGNITIONACTIVE 
0186: $BLIP1_FM4 = create_marker_above_car $BOMB_CAR

while 8121:   not player $PLAYER_CHAR in_zone 'REDLIGH'  // Red Light District
	wait 0 ms
	if and
		0119:   car $BOMB_CAR wrecked 
		0038:   $CAR_DEAD_ONCE == 0
	then
		0164: disable_marker $BLIP1_FM4 
		0004: $CAR_DEAD_ONCE = 1 
	end
end //while

014D: text_pager 'FM4_1' 140 10 2  // This is Maria. The car's a trap! Meet me at the slip south of Callahan Bridge.
018A: $BLIP2_FM4 = create_checkpoint_at 835.6875 -1091.688 -100.0 

while 80FA:   not player $PLAYER_CHAR stopped 1 827.25 -1084.25 6.75 radius 5.0 5.0 4.0 
	wait 0 ms
	if and
		0119:   car $BOMB_CAR wrecked 
		0038:   $CAR_DEAD_ONCE == 0
	then
		0164: disable_marker $BLIP1_FM4 
		0004: $CAR_DEAD_ONCE = 1 
	end
	if
		00F6:   player $PLAYER_CHAR 0 827.25 -1084.25 6.75 radius 5.0 5.0 4.0
	then
		goto @ON_FOOT_FRANK4
	end
end //while

:ON_FOOT_FRANK4

01B4: set_player $PLAYER_CHAR controllable 0 
01B6: set_weather WEATHER_SUNNY
0164: disable_marker $BLIP1_FM4 
0164: disable_marker $BLIP2_FM4 

//MEET MARIA ON THE INDUSTRIAL PIER CUT SCENE********************************************************************

01BD: $PRE_CUTSCENE_START_TIME = current_time_in_ms 
0004: $PRE_CUTSCENE_ELAPSED_TIME = 0 

while true
	if and
		83EE:   not player $PLAYER_CHAR controllable
		001A:   5000 > $PRE_CUTSCENE_ELAPSED_TIME 
	jf break // if player is not in control after 5 secs do the cutscene anyway
	wait 0 ms
	01BD: $PRE_CUTSCENE_CURRENT_TIME = current_time_in_ms 
	0084: $PRE_CUTSCENE_ELAPSED_TIME = $PRE_CUTSCENE_CURRENT_TIME 
	0060: $PRE_CUTSCENE_ELAPSED_TIME -= $PRE_CUTSCENE_START_TIME
end //while

03EF: player $PLAYER_CHAR make_safe 
0169: set_fade_color 0 0 0 
016A: fade 0 for 1500 ms 
03AF: set_streaming 0 
023C: load_special_actor 'MARIA' as 2 
023C: load_special_actor 'ASUKA' as 3 
02F3: load_object #CUTOBJ02 'MARIAH' 
02F3: load_object #CUTOBJ03 'ASUKAH'

while fading
	wait 0 ms
end

00A5: $BOAT_MAR = create_car #REEFER at 835.0 -1117.0 0.375
0175: set_car $BOAT_MAR z_angle_to 143.0 
0323: enable_boat $BOAT_MAR anchor 1 
02D4: boat_stop $BOAT_MAR 
038B: load_all_models_now 

while true
	if or
		823D:   not special_actor 2 loaded 
		823D:   not special_actor 3 loaded
		8248:   not model #CUTOBJ02 available 
		8248:   not model #CUTOBJ03 available 
	jf break
	wait 0 ms
end //while

02E4: load_cutscene_data 'S5_LRQB' 
0244: set_cutscene_pos 831.0625 -1114.188 0.125 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
02E5: $CUTSCENE_MARIA = create_cutscene_object #SPECIAL02 
02E6: set_cutscene_anim $CUTSCENE_MARIA 'MARIA' 
02E5: $CUTSCENE_ASUKA = create_cutscene_object #SPECIAL03 
02E6: set_cutscene_anim $CUTSCENE_ASUKA 'ASUKA' 
02F4: create_cutscene_actor $MARIA_CSHEAD from_head #CUTOBJ02 and_body $CUTSCENE_MARIA 
02F5: set_head_anim $MARIA_CSHEAD 'MARIA' 
02F4: create_cutscene_actor $ASUKA_CSHEAD from_head #CUTOBJ03 and_body $CUTSCENE_ASUKA 
02F5: set_head_anim $ASUKA_CSHEAD 'ASUKA'
if and
	8119:   not car $BOAT_MAR wrecked
	00E0:   is_player_in_any_car $PLAYER_CHAR 
then
	012A: put_player $PLAYER_CHAR at 835.375 -1108.688 1.0 and_remove_from_car
end

0369: put_player $PLAYER_CHAR in_car $BOAT_MAR 
016A: fade 1 for 1500 ms 
03AD: set_rubbish 0 
03AF: set_streaming 1 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime

// Displays cutscene text

while 001A:   697 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_2' duration 10000 ms flag 1  // Listen, Salvatore thinks that we're going behind his back,
while 001A:   3275 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_3' duration 10000 ms flag 1  // so he was offering you to the Cartel in order to make a deal.
while 001A:   6479 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_4' duration 10000 ms flag 1  // I couldn't let him do that, I mean the worst thing is,
while 001A:   9000 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_4B' duration 10000 ms flag 1  // it's all my fault... because I told him, we were an item.
while 001A:   11356 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_5' duration 10000 ms flag 1  // Don't ask me why. I don't know.
while 001A:   13307 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_6' duration 10000 ms flag 1  // Look you're a marked man on Mafia turf and I've got to get out of here too.
while 001A:   16285 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_6B' duration 10000 ms flag 1  // I've seen too much killing. Too much blood!
while 001A:   19438 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_7' duration 10000 ms flag 1  // This is a friend of mine ok, she's an old friend.. it's Asuka, she's someone we can trust.
while 001A:   23340 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_8' duration 10000 ms flag 1  // C'mon, Enough of the speeches.
while 001A:   24733 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'FM4_9' duration 10000 ms flag 1  // We better get out of here before we get more hysterical Italians wanting less friendly reunions.
while 001A:   30156 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BE: clear_prints 
while 001A:   31666 > $CUT_SCENE_TIME
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

03AD: set_rubbish 1 
02EA: end_cutscene 
01B4: set_player $PLAYER_CHAR controllable 1 
0373: set_camera_directly_behind_player 
009A: $MARIA = create_char PEDTYPE_SPECIAL model #SPECIAL02 at 837.0 -1116.5 1.75
02A9: set_char_only_damaged_by_player $MARIA to 1 
0173: set_actor $MARIA z_angle_to 17.0 
03FC: set_actor $MARIA stays_on_current_island 0 
0438: set_actor $MARIA ignore_level_transitions 1 
009A: $ASUKA = create_char PEDTYPE_SPECIAL model #SPECIAL03 at 836.5625 -1114.563 1.75
02A9: set_char_only_damaged_by_player $ASUKA to 1 
0173: set_actor $ASUKA z_angle_to 178.0 
03FC: set_actor $ASUKA stays_on_current_island 0 
0438: set_actor $ASUKA ignore_level_transitions 1 
03F9: make_actors $MARIA $ASUKA converse_in 999999 ms 
020E: actor $ASUKA look_at_actor $MARIA 

if
	8119:   not car $BOAT_MAR wrecked 
then
	018A: $FLAG_PLAYER_GOT_FRANK4_MESSAGE = create_checkpoint_at 565.25 -678.375 -100.0 
	0186: $BOAT_BLIP = create_marker_above_car $BOAT_MAR 
end

0001: wait 500 ms 
016A: fade 1 for 1000 ms 
0249: release_model #CUTOBJ02 
0249: release_model #CUTOBJ03

if
	8119:   not car $BOAT_MAR wrecked 
then
	0323: enable_boat $BOAT_MAR anchor 0  
end

0004: $FLAG_CAR_BLIP_DISPLAYED_FM4 = 1 

if or
	0118:   actor $ASUKA dead 
	0118:   actor $MARIA dead
then
	goto @MISSION_FAILED_FRANK4
end

while true
	if or
		8057:   not is_player_in_area_3d $PLAYER_CHAR coords 565.25 -678.375 -2.0 to 575.25 -688.375 4.0 sphere 1 
		80A4:   not is_char_in_area_3d $MARIA from 565.25 -678.375 -2.0 to 575.25 -688.375 4.0 sphere 0 
		80A4:   not is_char_in_area_3d $ASUKA from 565.25 -678.375 -2.0 to 575.25 -688.375 4.0 sphere 0 
	jf break
	wait 0 ms
	if
		0119:   car $BOAT_MAR wrecked
	then
		goto @MISSION_FAILED_FRANK4
	end
	if or
		0118:   actor $ASUKA dead 
		0118:   actor $MARIA dead
	then
		goto @MISSION_FAILED_FRANK4
	end
	if and
		00DC:   is_player_in_car $PLAYER_CHAR car $BOAT_MAR 
		0038:   $FLAG_CAR_BLIP_DISPLAYED_FM4 == 1 
	then
		0164: disable_marker $FLAG_PLAYER_GOT_FRANK4_MESSAGE 
		018A: $FLAG_PLAYER_GOT_FRANK4_MESSAGE = create_checkpoint_at 565.25 -678.375 -100.0 
		0164: disable_marker $BOAT_BLIP 
		0004: $FLAG_CAR_BLIP_DISPLAYED_FM4 = 0 
	end
	if and
		80DC:   not is_player_in_car $PLAYER_CHAR car $BOAT_MAR
		0038:   $FLAG_CAR_BLIP_DISPLAYED_FM4 == 0 
	then
		0164: disable_marker $BOAT_BLIP 
		0186: $BOAT_BLIP = create_marker_above_car $BOAT_MAR 
		0164: disable_marker $FLAG_PLAYER_GOT_FRANK4_MESSAGE 
		00BC: print_now 'IN_VEH' duration 5000 ms flag 1  // ~g~Hey! Get back in the vehicle!
		0004: $FLAG_CAR_BLIP_DISPLAYED_FM4 = 1 
	end
	if
		0038:   $GIRLS_ATTACK_PLAYER == 0 
	then
		if or
			8184:   not actor $ASUKA health >= 96 
			8184:   not actor $MARIA health >= 96 
		then
			if
				8118:   not actor $ASUKA dead 
			then
				01CA: actor $ASUKA kill_player $PLAYER_CHAR 
				01B2: give_actor $ASUKA weapon WEAPONTYPE_UZI ammo 200
			end
			if
				8118:   not actor $MARIA dead 
			then
				01CA: actor $MARIA kill_player $PLAYER_CHAR 
				01B2: give_actor $MARIA weapon WEAPONTYPE_UZI ammo 200
			end
			0004: $GIRLS_ATTACK_PLAYER = 1 
		end
	end
end //while

if
	8119:   not car $BOAT_MAR wrecked 
then
	0323: enable_boat $BOAT_MAR anchor 1 
	02D4: boat_stop $BOAT_MAR 
end

00BE: clear_prints 
0164: disable_marker $FLAG_PLAYER_GOT_FRANK4_MESSAGE 

//EVERYONE OFF THE BOAT IN COMMERCIAL CUTSCENE*******************************************************************

01BD: $PRE_CUTSCENE_START_TIME = current_time_in_ms 
0004: $PRE_CUTSCENE_ELAPSED_TIME = 0

while true
	if and
		83EE:   not player $PLAYER_CHAR controllable
		001A:   5000 > $PRE_CUTSCENE_ELAPSED_TIME 
	jf break // if player is not in control after 5 secs do the cutscene anyway
	wait 0 ms
	if
		0119:   car $BOAT_MAR wrecked
	then
		goto @MISSION_FAILED_FRANK4
	end
	if or
		0118:   actor $ASUKA dead 
		0118:   actor $MARIA dead
	then
		goto @MISSION_FAILED_FRANK4
	end
	01BD: $PRE_CUTSCENE_CURRENT_TIME = current_time_in_ms 
	0084: $PRE_CUTSCENE_ELAPSED_TIME = $PRE_CUTSCENE_CURRENT_TIME 
	0060: $PRE_CUTSCENE_ELAPSED_TIME -= $PRE_CUTSCENE_START_TIME
end //while

03EF: player $PLAYER_CHAR make_safe 
0169: set_fade_color 0 0 0 
016A: fade 0 for 1000 ms 

while fading
	wait 0 ms
end

044C: change_to_island LEVEL_COMMERCIAL

if and
	8119:   not car $BOAT_MAR wrecked 
	00E0:   is_player_in_any_car $PLAYER_CHAR 
then
	012A: put_player $PLAYER_CHAR at 515.0625 -650.875 16.0 and_remove_from_car 
end

0055: set_player_coordinates $PLAYER_CHAR to 515.0625 -650.875 16.0 
0171: set_player $PLAYER_CHAR z_angle_to 90.0 
03AF: set_streaming 0 
02F3: load_object #CUTOBJ01 'PLAYERH' 
02F3: load_object #CUTOBJ02 'MARIAH' 
02F3: load_object #CUTOBJ03 'ASUKAH' 
0247: request_model #YAKUZA 
0247: request_model #BLISTA 
038B: load_all_models_now 

while true
	if or
		8248:   not model #CUTOBJ01 available 
		8248:   not model #CUTOBJ02 available 
		8248:   not model #CUTOBJ03 available 
		8248:   not model #YAKUZA available 
		8248:   not model #BLISTA available
	jf break
	wait 0 ms
end //while

009B: delete_char $MARIA 
009B: delete_char $ASUKA 
02E4: load_cutscene_data 'S5_LRQC' 
0244: set_cutscene_pos 523.0625 -636.9375 15.5625 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
02E5: $CUTSCENE_MARIA = create_cutscene_object #SPECIAL02 
02E6: set_cutscene_anim $CUTSCENE_MARIA 'MARIA' 
02E5: $CUTSCENE_ASUKA = create_cutscene_object #SPECIAL03 
02E6: set_cutscene_anim $CUTSCENE_ASUKA 'ASUKA' 
02F4: create_cutscene_actor $PLAYER_CSHEAD from_head #CUTOBJ01 and_body $CUTSCENE_PLAYER 
02F5: set_head_anim $PLAYER_CSHEAD 'PLAYER' 
02F4: create_cutscene_actor $MARIA_CSHEAD from_head #CUTOBJ02 and_body $CUTSCENE_MARIA 
02F5: set_head_anim $MARIA_CSHEAD 'MARIA' 
02F4: create_cutscene_actor $ASUKA_CSHEAD from_head #CUTOBJ03 and_body $CUTSCENE_ASUKA 
02F5: set_head_anim $ASUKA_CSHEAD 'ASUKA' 
016A: fade 1 for 1000 ms 
0055: set_player_coordinates $PLAYER_CHAR to 515.0625 -650.875 16.0 
0171: set_player $PLAYER_CHAR z_angle_to 90.0 
00A5: $FRANK4_CAR1 = create_car #YAKUZA at 500.0 -659.375 -100.0
00A5: $FRANK4_CAR2 = create_car #BLISTA at 486.5 -643.5625 -100.0
0249: release_model #YAKUZA 
0249: release_model #BLISTA 
01C3: remove_references_to_car $FRANK4_CAR1 
01C3: remove_references_to_car $FRANK4_CAR2 
03AD: set_rubbish 0 
03AF: set_streaming 1 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime

// Displays cutscene text

while 001A:   12345 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'LRQC_1' duration 5000 ms flag 1  // Asuka and I are gonna have to talk, uh,
while 001A:   14476 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'LRQC_2' duration 5000 ms flag 1  // Why don't you go cruise around?
while 001A:   16021 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'LRQC_3' duration 5000 ms flag 1  // You'll need a place to lie low.
while 001A:   17946 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'LRQC_4' duration 5000 ms flag 1  // There's a warehouse at the edge of Belleville that should suit your needs.
while 001A:   21438 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'LRQC_5' duration 5000 ms flag 1  // Come back here to my Condo when you are ready,
while 001A:   23312 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: print_now 'LRQC_6' duration 5000 ms flag 1  // and we can have a little chat.
while 001A:   25177 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BE: clear_prints 
while 001A:   28000 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end

016A: fade 0 for 1000 ms

while 82E9:   not cutscene_reached_end
	wait 0 ms
end

00BE: clear_prints 

while fading
	wait 0 ms
end

03AD: set_rubbish 1 
02EA: end_cutscene 
03C8: set_camera_directly_before_player 
0001: wait 500 ms 
016A: fade 1 for 1000 ms 
0296: unload_special_actor 2 
0296: unload_special_actor 3 
0249: release_model #CUTOBJ01 
0249: release_model #CUTOBJ02 
0249: release_model #CUTOBJ03 
01B7: release_weather 
goto @MISSION_PASSED_FRANK4

/////////////////////////////////////////

// Mission Failed

:MISSION_FAILED_FRANK4
00BA: print_big 'M_FAIL' duration 5000 ms style 1  // MISSION FAILED!
goto @MISSION_END_FRANK4

/////////////////////////////////////////

// Mission Passed

:MISSION_PASSED_FRANK4
0004: $LAST_REQUESTS_COMPLETED = 1 
0004: $FLAG_STAUNTON_OPEN = 1 
0394: play_mission_passed_music 1 
034A: portland_complete 
0004: $BRIDGE_IS_DAMAGED = 1 
//SWAP BRIDGE FROM DAMAGED TO FIXED
03B6: replace_model_at 525.3125 -927.0625 71.8125 radius 20.0 from #NBBRIDGFK2 to #NBBRIDGCABLS01 
03B6: replace_model_at 706.375 -935.8125 67.0625 radius 20.0 from #NBBRIDGFK2 to #NBBRIDGCABLS01 
03B6: replace_model_at 529.0 -920.0625 43.5 radius 20.0 from #DAMGBRIDGERDB to #NBBRIDGERDB 
03B6: replace_model_at 702.75 -939.9375 38.6875 radius 20.0 from #DAMGBRIDGERDB to #NBBRIDGERDB 
03B6: replace_model_at 529.0 -942.9375 43.5 radius 20.0 from #DAMGBBRIDGERDA to #NBBRIDGERDA 
03B6: replace_model_at 702.75 -919.9375 38.6875 radius 20.0 from #DAMGBBRIDGERDA to #NBBRIDGERDA 
03B6: replace_model_at 525.3125 -927.0625 71.8125 radius 20.0 from #LODRIDGFK2 to #LODRIDGCABLS01 
03B6: replace_model_at 706.375 -935.8125 67.0625 radius 20.0 from #LODRIDGFK2 to #LODRIDGCABLS01 
03B6: replace_model_at 521.125 -922.9375 43.5 radius 20.0 from #LODGBRIDGERDB to #LODRIDGERDB 
03B6: replace_model_at 702.75 -939.9375 38.6875 radius 20.0 from #LODGBRIDGERDB to #LODRIDGERDB 
03B6: replace_model_at 529.0 -940.0625 43.5 radius 20.0 from #LODGBBRIDGERDA to #LODRIDGERDA 
03B6: replace_model_at 702.75 -919.9375 38.6875 radius 20.0 from #LODGBBRIDGERDA to #LODRIDGERDA 
0363: toggle_model_render_at 1027.25 -933.75 15.0 radius 50.0 object #INDHELIX_BARRIER 0 
03B6: replace_model_at 1027.25 -933.75 15.0 radius 50.0 from #INDHELIX_BARRIER to #LOD_LAND014 
0108: destroy_object $CHINA_SUBWAY_GATE 
0108: destroy_object $TUNNEL_PORTLAND_BLOCK 
if
	0038:   $AMMU2_BLIP_ADDED == 0 
then
	02A8: $STAUNTON_AMMUNATION_MARKER = create_marker RADAR_SPRITE_WEAPON at 345.5 -713.5 26.0625 
	02A8: $STAUNTON_PAYNSPRAY_MARKER = create_marker RADAR_SPRITE_SPRAY at 379.0 -493.75 25.1875 
	0004: $AMMU2_BLIP_ADDED = 1
end
01E7: switch_roads_on 619.5625 -911.5 45.0 to 834.25 -954.5 32.0 //Industrial to commercial Bridge
01E7: switch_roads_on 659.5625 200.0 -20.0 to 945.75 147.5 5.0 //Tunnel
01E7: switch_roads_on 529.5625 106.5 -30.0 to 581.375 65.6875 0.0  //tunnel from commercial to intersection for sub and ind
0318: set_latest_mission_passed 'FM4'  // 'LAST REQUESTS'
030C: set_mission_points += 1 
014C: set_parked_car_generator $COM_CAR23 cars_to_generate_to 101 
0164: disable_marker $SALVATORE_MISSION_MARKER 
0164: disable_marker $LUIGI_MISSION_MARKER 
0164: disable_marker $TONI_MISSION_MARKER 
0164: disable_marker $JOEY_MISSION_MARKER 
01E3: text_1number_styled 'M_PASS' number 20000 duration 5000 ms style 1  // MISSION PASSED! $~1~
0109: player $PLAYER_CHAR money += 20000 
004F: create_thread @ASUKA_MISSION1_LOOP 
0164: disable_marker $ASUKA1_MISSION_MARKER 
02A7: $ASUKA1_MISSION_MARKER = create_icon_marker_and_sphere RADAR_SPRITE_ASUKA at 523.6875 -643.0 16.0625 
goto @MISSION_END_FRANK4

/////////////////////////////////////////

// Mission Cleanup

:MISSION_CLEANUP_FRANK4
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_FRANK = 0 
0164: disable_marker $BLIP1_FM4 
0164: disable_marker $BLIP2_FM4 
0164: disable_marker $FLAG_PLAYER_GOT_FRANK4_MESSAGE 
0164: disable_marker $BOAT_BLIP 
01B7: release_weather 
0249: release_model #REEFER 
0249: release_model #CHEETAH 
0249: release_model #YAKUZA 
0249: release_model #BLISTA 
034F: destroy_actor_with_fade $MARIA 
034F: destroy_actor_with_fade $ASUKA 
0296: unload_special_actor 2 
0296: unload_special_actor 3 
00D8: mission_has_finished 
return

