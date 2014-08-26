:LUIGI_MESSAGE
03A4: name_thread 'LUIHELP'
wait 1000 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if and
		0038:   $INTRO_HELP_MESSAGE == 0
		0038:   $FLAG_LUIGI_MISSION1_PASSED == 1
	then
		03E5: text_box 'LM1_8'   // You can go and see Luigi for more work or check out Liberty City.
		0004: $INTRO_HELP_MESSAGE = 1
	end
	if
		0038:   $INTRO_HELP_MESSAGE == 1
	then
		16@ = 0
		0004: $INTRO_HELP_MESSAGE = 2
	end
	while 001B: 6000 > 16@
		wait 0 ms
	end
	if and
		0038:   $INTRO_HELP_MESSAGE == 2
		0038:   $ONMISSION == 0
	then
		03E5: text_box 'LM1_8A'   // To earn some extra cash why not 'borrow' a taxi...
		16@ = 0
		0004: $INTRO_HELP_MESSAGE = 3
	end
end
end_thread
			

//#####################################################################################
//#####################################################################################
// END LUIGI HELP / BEGIN AMMUNATION PISTOL MONITOR 
//#####################################################################################
//#####################################################################################

:PISTOL_MESSAGE
03A4: name_thread 'PISTOL1'

while true
	wait 10000 ms
	if and
		0256:   is_player $PLAYER_CHAR defined 
		0038:   $ONMISSION == 0
		0038:   $DISPLAYED_PISTOL_NOW_AT_AMMUNITION_HELP_TEXT == 0
	then
		0215: destroy_pickup $SHOP_PISTOL
		0213: $AMMUNATION_COLT_PICKUP = create_pickup #COLT45 type PICKUP_IN_SHOP at 1068.5 -400.75 15.1875 
		014D: text_pager 'COLT_IN' 140 2 0  // The Pistol is now in stock at Ammunation!
		0004: $DISPLAYED_PISTOL_NOW_AT_AMMUNITION_HELP_TEXT = 1
		end_thread
	end
end //while


//#####################################################################################
//#####################################################################################
// END AMMUNATION PISTOL MONITOR / BEGIN AMMUNATION UZI MONITOR
//#####################################################################################
//#####################################################################################

:UZI_MESSAGE
03A4: name_thread 'UZI1'

while true
	wait 5000 ms
	if and
		0256:   is_player $PLAYER_CHAR defined 
		0038:   $ONMISSION == 0
		0038:   $DISPLAYED_UZI_NOW_AT_AMMUNITION_HELP_TEXT == 0
	then
		0213: $AMMUNATION_UZI_PICKUP = create_pickup #UZI type PICKUP_IN_SHOP at 1070.5 -400.75 15.1875
		014D: text_pager 'UZI_IN' 140 2 0  // The Uzi is now in stock at Ammunation!
		0004: $DISPLAYED_UZI_NOW_AT_AMMUNITION_HELP_TEXT = 1
		end_thread
	end
end //while

//#####################################################################################
//#####################################################################################
// END AMMUNATION UZI MONITOR / BEGIN IE/CRANE/SECURICAR PAGER MESSAGE
//#####################################################################################
//#####################################################################################

:IMP_EXP_PAGER
03A4: name_thread 'IMPEXPP'

while true
	wait 100000 ms
	if and
		0038:   $ONMISSION == 0
		0256:   is_player $PLAYER_CHAR defined 
	then
		014D: text_pager 'IMPEXPP' 140 2 0  // Import/Export garage, Portland Harbor. We have orders for various vehicles. Check our notice board for our requirements.
		end_thread
	end
end //while


:EMERGENCY_CRANE_PAGER
03A4: name_thread 'EMVHPUP'

while true
	wait 200000 ms
	if and
		0038:   $ONMISSION == 0
		0256:   is_player $PLAYER_CHAR defined 
	then
		014D: text_pager 'EMVHPUP' 140 2 0  // Great rates paid for new and used Emergency Vehicles. Bring them to the crane in the north east of Portland Harbor.
		end_thread
	end
end //while


:VAN_HEIST_GARAGE_PAGER
03A4: name_thread 'VANHSTP'

while true
	wait 300000 ms
	if and
		0038:   $ONMISSION == 0
		0256:   is_player $PLAYER_CHAR defined 
	then
		014D: add_pager_message 'VANHSTP'  140  2  0   // Any more Securicars you want cracked? Bring them to our garage in the Portland Harbor.
		end_thread
	end
end //while

//#####################################################################################
//#####################################################################################
// END IE/CRANE/SECURICAR PAGER MESSAGE / BEGIN JOEY BF INJECTION MONITOR
//#####################################################################################
//#####################################################################################

:JOEYS_BUGGY_LOOP
03A4: name_thread 'JOE_BUG'

while true
	wait 500 ms
	if and
		0256:   is_player $PLAYER_CHAR defined 
		03C6:   current_island == LEVEL_INDUSTRIAL
	then
		00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES 
		if and
			0018:   $CURRENT_TIME_HOURS > 17
			001A:   24 > $CURRENT_TIME_HOURS
		then
			if
				0038:   $JOEY_VISITING_MISTY == 0
			then
				014C: set_parked_car_generator $JOEYS_BUGGY cars_to_generate_to 101
				018D: $JOEY_SOUND = create_sound 91 at 937.0625 -275.5 8.875
				0004: $JOEY_VISITING_MISTY = 1
			end
		else
			if
				0038:   $JOEY_VISITING_MISTY == 1
			then
				014C: set_parked_car_generator $JOEYS_BUGGY cars_to_generate_to 0
				018E: stop_sound $JOEY_SOUND 
				0004: $JOEY_VISITING_MISTY = 0
			end
		end
	end
end

//#####################################################################################
//#####################################################################################
// END JOEY BF INJECTION MONITOR / BEGIN TONI_FR
//#####################################################################################
//#####################################################################################

:TONI5_FLAMES_LOOP
03A4: name_thread 'TONI_FR'

while true
	wait 500 ms
	if
		0256:   is_player $PLAYER_CHAR defined 
	then
		if
			8121:   not player $PLAYER_CHAR in_zone 'PORT_W'  // Callahan Point
		then
			031A: remove_all_fires
			end_thread
		end
	end
end //while

//#####################################################################################
//#####################################################################################
// END TONI_FR / BEGIN BLOB_HP
//#####################################################################################
//#####################################################################################

:BLOB_HELP_LOOP
03A4: name_thread 'BLOB_HP'

while true
	wait 100 ms
	if
		0256:   is_player $PLAYER_CHAR defined 
	then
		if	
			019C:   player $PLAYER_CHAR 0 895.25 -428.0 12.0 900.25 -423.1875 18.0
		then
			03E5: text_box 'HELP12'  // Walk into the center of the blue marker to trigger a mission.
			end_thread
		end
	end
end //while

//#####################################################################################
//#####################################################################################
// END BLOB_HP / BEGIN TONI4PG
//#####################################################################################
//#####################################################################################

:TONI4_PAGER_LOOP
03A4: name_thread 'TONI4PG'

while true
	wait 10000 ms
	if and
		0256:   is_player $PLAYER_CHAR defined 
		0038:   $ONMISSION == 0
		03C6:   current_island == LEVEL_INDUSTRIAL
	then
		if
			0038:   $TRIADS_AND_TRIBULATIONS_COMPLETED == 0
		then
			014D: text_pager 'TONI_P' 140 2 0  // I've got some urgent work for you! -Toni
		end
		004F: create_thread @TONI5_PAGER_LOOP
		end_thread
	end
end //while

//#####################################################################################
//#####################################################################################
// END TONI4PG / BEGIN TONI5PG
//#####################################################################################
//#####################################################################################

:TONI5_PAGER_LOOP
03A4: name_thread 'TONI5PG'

while true
	wait 10000 ms
	if and
		0256:   is_player $PLAYER_CHAR defined 
		0038:   $ONMISSION == 0
		03C6:   current_island == LEVEL_INDUSTRIAL
	then
		if
			0038:   $TRIADS_AND_TRIBULATIONS_COMPLETED == 1
		then
			if
				0038:   $BLOW_FISH_COMPLETED == 0
			then
				014D: text_pager 'TONI_P' 140 2 0  // I've got some urgent work for you! -Toni
			end
			end_thread
		end
	end
end //while

//#####################################################################################
//#####################################################################################
// END TONI5PG / BEGIN FUZZDR
//#####################################################################################
//#####################################################################################

:CLOSE_FUZZ_DOORS
03A4: name_thread 'FUZZ_DR'

while true
	if or
		0038:   $FLAG_MOVED_DOOR1_LM5 == 0
		0038:   $FLAG_MOVED_DOOR2_LM5 == 0
	jf break
	wait 0 ms
	if and
		0038:   $COUNTER_GIRLS_TRYING_TO_GET_TO_BALL == 0
		8339:   not objects_in_cube 1008.0 -899.0 14.0 to 996.5 -886.5 20.0 flags 0 1 1 0 1
	then
		if
			034D: rotate_object $FUZZBALL_DOOR1 from_angle 180.0 to 10.0 collision_check 0
		then
			0004: $FLAG_MOVED_DOOR1_LM5 = 1
		end
		if
			034D: rotate_object $FUZZBALL_DOOR2 from_angle 0.0 to 10.0 collision_check 0 
		then
			0004: $FLAG_MOVED_DOOR2_LM5 = 1
		end
	end
end //while
end_thread

//#####################################################################################
//#####################################################################################
// END FUZZ DR / BEGIN ASUK_DR
//#####################################################################################
//#####################################################################################

:CLOSE_ASUKA1_DOOR
03A4: name_thread 'ASUK_DR'
if
	03CA:   object $LUIGI_BACKDOOR exists
then
	0176: $DOOR_POSITION_A1 = object $LUIGI_BACKDOOR z_angle
	if
		0038:   $DOOR_CRASH_FLAG == 1
	then
		while 8042:   not $DOOR_POSITION_A1 == 0.0
			if
				0256:   is_player $PLAYER_CHAR defined 
			then
				if
					8057:   not is_player_in_area_3d $PLAYER_CHAR coords 889.5625 -418.0625 15.0 to 895.125 -412.625 18.0 sphere 0
				then
					if and
						0020:   $DOOR_POSITION_A1 > -10.0
						0022:   10.0 > $DOOR_POSITION_A1
					then
						0005: $DOOR_POSITION_A1 = 0.0
					else
						000D: $DOOR_POSITION_A1 -= 10.0
					end
					0177: set_object $LUIGI_BACKDOOR z_angle_to $DOOR_POSITION_A1
				end
			else
				0005: $DOOR_POSITION_A1 = 0.0
				0177: set_object $LUIGI_BACKDOOR z_angle_to $DOOR_POSITION_A1
			end
			wait 0 ms
		end //while
	end
end
end_thread
