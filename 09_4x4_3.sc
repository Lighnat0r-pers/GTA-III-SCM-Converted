// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***************************************GRIPPED, SORTED*********************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

:M09_GRIPPED
gosub @MISSION_START_4X4THREE
if
	0112:   has_deatharrest_been_executed 
then
	gosub @MISSION_4X4THREE_FAILED
end

:MISSION_END_4X4THREE
gosub @MISSION_CLEANUP_4X4THREE
end_thread

// ****************************************Mission Start************************************

:MISSION_START_4X4THREE
0317: increment_mission_attempts 
03A4: name_thread 'T4X4_3' 
0004: $ONMISSION = 1 
00BA: print_big 'T4X4_3' duration 5000 ms style 2  // 'GRIPPED!'
0001: wait 0 ms 

// Set Variables

0004: $COUNTER_4X4_PICKUPS = 0 
0004: $TIMER_4X4 = 0 
0004: $FLAG_INTRO = 0 
0004: $FLAG_TIMER = 0 
0004: $ON_GRIPPED_MISSION = 1 
0004: $FLAG_INTRO_JUMP = 0 

0004: $FLAG_BLIP_1 = 0 
0004: $FLAG_BLIP_2 = 0 
0004: $FLAG_BLIP_3 = 0 
0004: $FLAG_BLIP_4 = 0 
0004: $FLAG_BLIP_5 = 0 
0004: $FLAG_BLIP_6 = 0 
0004: $FLAG_BLIP_7 = 0 
0004: $FLAG_BLIP_8 = 0 
0004: $FLAG_BLIP_9 = 0 
0004: $FLAG_BLIP_10 = 0 
0004: $FLAG_BLIP_11 = 0 
0004: $FLAG_BLIP_12 = 0 
0004: $FLAG_BLIP_13 = 0 
0004: $FLAG_BLIP_14 = 0 
0004: $FLAG_BLIP_15 = 0 
0004: $FLAG_BLIP_16 = 0 
0004: $FLAG_BLIP_17 = 0 
0004: $FLAG_BLIP_18 = 0 
0004: $FLAG_BLIP_19 = 0 
0004: $FLAG_BLIP_20 = 0 

0004: $RECORD_TEMP = 0 

// Set Coords

0005: $X_1 = -236.5 
0005: $Y_1 = 188.75 
0005: $Z_1 = 11.5625 
0005: $X_2 = -288.5625 
0005: $Y_2 = 153.6875 
0005: $Z_2 = 8.375 
0005: $X_3 = -346.75 
0005: $Y_3 = 158.0 
0005: $Z_3 = 25.0 
0005: $X_4 = -399.75 
0005: $Y_4 = 194.5625 
0005: $Z_4 = 50.6875 
0005: $X_5 = -389.375 
0005: $Y_5 = 195.6875 
0005: $Z_5 = 50.25 
0005: $X_6 = -335.875 
0005: $Y_6 = 202.1875 
0005: $Z_6 = 54.6875 
0005: $X_7 = -445.25 
0005: $Y_7 = 205.5625 
0005: $Z_7 = 63.75 
0005: $X_8 = -296.875 
0005: $Y_8 = 262.375 
0005: $Z_8 = 66.6875 
0005: $X_9 = -210.1875 
0005: $Y_9 = 399.875 
0005: $Z_9 = 98.25 
0005: $X_10 = -187.5625 
0005: $Y_10 = 557.5625 
0005: $Z_10 = 141.5625 
0005: $X_11 = -230.0625 
0005: $Y_11 = 609.25 
0005: $Z_11 = 138.1875 
0005: $X_12 = -393.5 
0005: $Y_12 = 502.75 
0005: $Z_12 = 150.25 
0005: $X_13 = -526.5 
0005: $Y_13 = 497.1875 
0005: $Z_13 = 165.375 
0005: $X_14 = -393.1875 
0005: $Y_14 = 403.75 
0005: $Z_14 = 133.875 
0005: $X_15 = -499.375 
0005: $Y_15 = 407.875 
0005: $Z_15 = 116.1875 
0005: $X_16 = -686.5625 
0005: $Y_16 = 386.25 
0005: $Z_16 = 103.0625 
0005: $X_17 = -744.375 
0005: $Y_17 = 507.75 
0005: $Z_17 = 159.0 
0005: $X_18 = -814.875 
0005: $Y_18 = 563.0625 
0005: $Z_18 = 120.5 
0005: $X_19 = -879.875 
0005: $Y_19 = 585.6875 
0005: $Z_19 = 94.0 
0005: $X_20 = -829.25 
0005: $Y_20 = 412.25 
0005: $Z_20 = 93.5 

//Mission Script

01C0: $WANTED_4X4 = player $PLAYER_CHAR wanted_level 
0110: clear_player $PLAYER_CHAR wanted_level 
00DA: $PLAYER_4X4 = store_car_player_is_in $PLAYER_CHAR
if
	8119:   not car $PLAYER_4X4 wrecked 
then
	020A: set_car $PLAYER_4X4 door_status_to CARLOCK_UNLOCKED
end
01B4: set_player $PLAYER_CHAR controllable 0 
02A3: toggle_widescreen 1 

018A: $BLIP_1 = create_checkpoint_at $X_1 $Y_1 $Z_1 
018A: $BLIP_2 = create_checkpoint_at $X_2 $Y_2 $Z_2 
018A: $BLIP_3 = create_checkpoint_at $X_3 $Y_3 $Z_3 
018A: $BLIP_4 = create_checkpoint_at $X_4 $Y_4 $Z_4 
018A: $BLIP_5 = create_checkpoint_at $X_5 $Y_5 $Z_5 
018A: $BLIP_6 = create_checkpoint_at $X_6 $Y_6 $Z_6 
018A: $BLIP_7 = create_checkpoint_at $X_7 $Y_7 $Z_7 
018A: $BLIP_8 = create_checkpoint_at $X_8 $Y_8 $Z_8 
018A: $BLIP_9 = create_checkpoint_at $X_9 $Y_9 $Z_9 
018A: $BLIP_10 = create_checkpoint_at $X_10 $Y_10 $Z_10 
018A: $BLIP_11 = create_checkpoint_at $X_11 $Y_11 $Z_11 
018A: $BLIP_12 = create_checkpoint_at $X_12 $Y_12 $Z_12 
018A: $BLIP_13 = create_checkpoint_at $X_13 $Y_13 $Z_13 
018A: $BLIP_14 = create_checkpoint_at $X_14 $Y_14 $Z_14 
018A: $BLIP_15 = create_checkpoint_at $X_15 $Y_15 $Z_15 
018A: $BLIP_16 = create_checkpoint_at $X_16 $Y_16 $Z_16 
018A: $BLIP_17 = create_checkpoint_at $X_17 $Y_17 $Z_17 
018A: $BLIP_18 = create_checkpoint_at $X_18 $Y_18 $Z_18 
018A: $BLIP_19 = create_checkpoint_at $X_19 $Y_19 $Z_19 
018A: $BLIP_20 = create_checkpoint_at $X_20 $Y_20 $Z_20 

while 001A:   20 > $COUNTER_4X4_PICKUPS 
	wait 0 ms
	if and
		0038:   $COUNTER_4X4_PICKUPS == 1 
		0038:   $FLAG_TIMER == 0 
	then
		014E: start_timer_at $TIMER_4X4 
		0004: $FLAG_TIMER = 1 
	end
	if
		0038: $FLAG_BLIP_1 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_1 $Y_1 $Z_1
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_1 $Y_1 $Z_1 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_1 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_1 $Y_1 $Z_1 
			0004: $FLAG_BLIP_1 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_2 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_2 $Y_2 $Z_2
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_2 $Y_2 $Z_2 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_2 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_2 $Y_2 $Z_2 
			0004: $FLAG_BLIP_2 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_3 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_3 $Y_3 $Z_3
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_3 $Y_3 $Z_3 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_3 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_3 $Y_3 $Z_3 
			0004: $FLAG_BLIP_3 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_4 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_4 $Y_4 $Z_4
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_4 $Y_4 $Z_4 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_4 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_4 $Y_4 $Z_4 
			0004: $FLAG_BLIP_4 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_5 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_5 $Y_5 $Z_5
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_5 $Y_5 $Z_5 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_5 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_5 $Y_5 $Z_5 
			0004: $FLAG_BLIP_5 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_6 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_6 $Y_6 $Z_6
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_6 $Y_6 $Z_6 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_6 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_6 $Y_6 $Z_6 
			0004: $FLAG_BLIP_6 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_7 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_7 $Y_7 $Z_7
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_7 $Y_7 $Z_7 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_7 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_7 $Y_7 $Z_7 
			0004: $FLAG_BLIP_7 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_8 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_8 $Y_8 $Z_8
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_8 $Y_8 $Z_8 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_8 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_8 $Y_8 $Z_8 
			0004: $FLAG_BLIP_8 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_9 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_9 $Y_9 $Z_9
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_9 $Y_9 $Z_9 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_9 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_9 $Y_9 $Z_9 
			0004: $FLAG_BLIP_9 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_10 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_10 $Y_10 $Z_10
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_10 $Y_10 $Z_10 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_10 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_10 $Y_10 $Z_10 
			0004: $FLAG_BLIP_10 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_11 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_11 $Y_11 $Z_11
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_11 $Y_11 $Z_11 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_11 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_11 $Y_11 $Z_11 
			0004: $FLAG_BLIP_11 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_12 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_12 $Y_12 $Z_12
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_12 $Y_12 $Z_12 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_12 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_12 $Y_12 $Z_12 
			0004: $FLAG_BLIP_12 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_13 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_13 $Y_13 $Z_13
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_13 $Y_13 $Z_13 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_13 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_13 $Y_13 $Z_13 
			0004: $FLAG_BLIP_13 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_14 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_14 $Y_14 $Z_14
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_14 $Y_14 $Z_14 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_14 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_14 $Y_14 $Z_14 
			0004: $FLAG_BLIP_14 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_15 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_15 $Y_15 $Z_15
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_15 $Y_15 $Z_15 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_15 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_15 $Y_15 $Z_15 
			0004: $FLAG_BLIP_15 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_16 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_16 $Y_16 $Z_16
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_16 $Y_16 $Z_16 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_16 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_16 $Y_16 $Z_16 
			0004: $FLAG_BLIP_16 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_17 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_17 $Y_17 $Z_17
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_17 $Y_17 $Z_17 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_17 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_17 $Y_17 $Z_17 
			0004: $FLAG_BLIP_17 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_18 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_18 $Y_18 $Z_18
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_18 $Y_18 $Z_18 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_18 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_18 $Y_18 $Z_18 
			0004: $FLAG_BLIP_18 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_19 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_19 $Y_19 $Z_19
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_19 $Y_19 $Z_19 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_19 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_19 $Y_19 $Z_19 
			0004: $FLAG_BLIP_19 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end
	if
		0038: $FLAG_BLIP_20 == 0
	then
		024F: create_corona 1.0 CORONATYPE_HEX FLARETYPE_NONE with_color 0 200 200 at_point $X_20 $Y_20 $Z_20
		if
			00F7:   player $PLAYER_CHAR sphere 0 near_point_in_car $X_20 $Y_20 $Z_20 radius 2.5 2.5 3.5 
		then
			0164: disable_marker $BLIP_20 
			018C: play_sound SOUND_PART_MISSION_COMPLETE at $X_20 $Y_20 $Z_20 
			0004: $FLAG_BLIP_20 = 1 
			gosub @MISSION_4X4THREE_CHECKPOINT_PICKED_UP
		end
	end

	if
		0038:   $GRIPPED_HELP_FINISHED == 1 
	then
		if and
			0038:   $FLAG_INTRO_JUMP == 0 
			001A:   4 > $FLAG_INTRO 
		then
			if
				00E1:   is_button_pressed PAD1 button CROSS
			then
				0004: $INTRO_TIME_LAPSED = 8501 
				0004: $FLAG_INTRO = 3 
				0004: $FLAG_INTRO_JUMP = 1
			end
		end
	end
	if
		0038:   $FLAG_INTRO == 0 
	then
		01BD: $TIMER_INTRO_START = current_time_in_ms 
		015F: set_camera_position -328.0 132.0 25.0 0.0 rotation 0.0 0.0 
		043C: set_game_sounds_fade 0 
		016A: fade 0 for 1500 ms
		while fading
			wait 0 ms
		end
		03CB: load_scene -328.0 132.0 25.0 
		0160: point_camera $X_2 $Y_2 $Z_2 switchstyle JUMP_CUT
		016A: fade 1 for 1500 ms 
		while fading
			wait 0 ms
		end
		00BC: print_now 'T4X4_3A' duration 5000 ms flag 1  // ~g~You have ~y~5 minutes~g~ to collect ~y~20~g~ checkpoints. ~g~You may collect them in ~y~ANY ORDER.
		0004: $FLAG_INTRO = 1
	end
	if
		0038: $FLAG_INTRO_JUMP == 0
	then
		01BD: $TIMER_INTRO_NOW = current_time_in_ms 
		0084: $INTRO_TIME_LAPSED = $TIMER_INTRO_NOW 
		0060: $INTRO_TIME_LAPSED -= $TIMER_INTRO_START 
	end
	if and
		0018:   $INTRO_TIME_LAPSED > 4500 
		0038:   $FLAG_INTRO == 1
	then
		0160: point_camera $X_3 $Y_3 $Z_3 switchstyle INTERPOLATION
		0004: $FLAG_INTRO = 2
	end
	if and
		0018:   $INTRO_TIME_LAPSED > 6000 
		0038:   $FLAG_INTRO == 2 
	then
		0160: point_camera $X_4 $Y_4 $Z_4 switchstyle INTERPOLATION
		00BC: print_now 'T4X4_3B' duration 5000 ms flag 1  // ~y~PASS THROUGH~g~ the first checkpoint to start the timer. ~g~Each checkpoint will credit you with ~y~15 SECONDS~g~.
		0004: $FLAG_INTRO = 3
	end
	if and
		0018:   $INTRO_TIME_LAPSED > 8500 
		0038:   $FLAG_INTRO == 3
	then
		016A: fade 0 for 1500 ms
		while fading
			wait 0 ms
		end
		00BE: clear_prints 
		03CB: load_scene -230.0 270.0 20.0
		02EB: restore_camera_jumpcut 
		02A3: toggle_widescreen 0 
		01B4: set_player $PLAYER_CHAR controllable 1 
		if
			8119:   not car $PLAYER_4X4 wrecked 
		then
			020A: set_car $PLAYER_4X4 door_status_to CARLOCK_UNLOCKED
		end
		010D: set_player $PLAYER_CHAR wanted_level_to $WANTED_4X4 
		016A: fade 1 for 1500 ms
		while fading
			wait 0 ms
		end
		043C: set_game_sounds_fade 1 
		0004: $FLAG_INTRO = 4 
		0004: $GRIPPED_HELP_FINISHED = 1 
	end
	if
		0038:   $FLAG_TIMER == 1 
	then
		if
			001A:   1 > $TIMER_4X4
		then
			00BC: print_now 'TAXI2' duration 3000 ms flag 1  // ~r~You're out of time!
			goto @MISSION_4X4THREE_FAILED
		end
	end
	if
		80DE:   not is_player_in_model $PLAYER_CHAR model #PATRIOT
	then
		00BC: print_now 'T4X4_F' duration 3000 ms flag 1  // ~r~You bailed! Too tough for you?!
		goto @MISSION_4X4THREE_FAILED
	end
end
goto @MISSION_4X4THREE_PASSED

/////////////////////////////////////////

:MISSION_4X4THREE_CHECKPOINT_PICKED_UP
0008: $COUNTER_4X4_PICKUPS += 1 
0008: $TIMER_4X4 += 15000 
01E5: text_1number_highpriority 'T4X4_3C' number $COUNTER_4X4_PICKUPS duration 3000 ms flag 1  // ~1~ of 20!
return

// --------------------------Mission failed-----------------------------------------------

:MISSION_4X4THREE_FAILED
00BA: print_big 'M_FAIL' duration 2000 ms style 1  // MISSION FAILED!
goto @MISSION_END_4X4THREE

// -------------------------Mission passed-------------------------------------------------

:MISSION_4X4THREE_PASSED
0004: $RECORD_TEMP = 300000 
0060: $RECORD_TEMP -= $TIMER_4X4 
0014: $RECORD_TEMP /= 1000 
if or
	0038:   $GRIPPED_COMPLETED == 0
	001C:   $GRIPPED_BEST_TIME > $RECORD_TEMP
then
	0084: $GRIPPED_BEST_TIME = $RECORD_TEMP 
end
01E3: text_1number_styled 'M_PASS' number 40000 duration 5000 ms style 1  // MISSION PASSED! $~1~
0394: play_music 1 
0110: clear_player $PLAYER_CHAR wanted_level 
0109: player $PLAYER_CHAR money += 40000 
03FF: save_offroadIII_time $GRIPPED_BEST_TIME
if
	0038:   $GRIPPED_COMPLETED == 0
then
	0318: set_latest_mission_passed 'T4X4_3'  // 'GRIPPED!'
	0004: $GRIPPED_COMPLETED = 1 
	030C: set_mission_points += 1 
end
goto @MISSION_END_4X4THREE

/////////////////////////////////////////

// mission cleanup

:MISSION_CLEANUP_4X4THREE
02EB: restore_camera_jumpcut 
02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
0164: disable_marker $BLIP_1 
0164: disable_marker $BLIP_2 
0164: disable_marker $BLIP_3 
0164: disable_marker $BLIP_4 
0164: disable_marker $BLIP_5 
0164: disable_marker $BLIP_6 
0164: disable_marker $BLIP_7 
0164: disable_marker $BLIP_8 
0164: disable_marker $BLIP_9 
0164: disable_marker $BLIP_10 
0164: disable_marker $BLIP_11 
0164: disable_marker $BLIP_12 
0164: disable_marker $BLIP_13 
0164: disable_marker $BLIP_14 
0164: disable_marker $BLIP_15 
0164: disable_marker $BLIP_16 
0164: disable_marker $BLIP_17 
0164: disable_marker $BLIP_18 
0164: disable_marker $BLIP_19 
0164: disable_marker $BLIP_20 
014F: stop_timer $TIMER_4X4 
0004: $ONMISSION = 0 
00D8: mission_has_finished 
return 
