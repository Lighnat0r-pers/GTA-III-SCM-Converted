//#####################################################################################
//#####################################################################################
// BEGIN HEALTH HELP MONITOR
//#####################################################################################
//#####################################################################################

:WASTED_LOOP
wait 0 ms
if
	0256:   is_player $PLAYER_CHAR defined
then
	if and
		0121:   player $PLAYER_CHAR in_zone 'S_VIEW'  // Portland View
		0038:   $ONMISSION == 0
		0038:   $HEAL_INFO_TRIP == 1
		0214:   pickup $WASTED_INFO_PICKUP picked_up
	then
		01B4: set_player $PLAYER_CHAR controllable 0 
		while fading
			wait 0 ms
		end
		0417: start_mission M01_HOSPITALHELP
		end_thread
	end
end
goto @WASTED_LOOP

//#####################################################################################
//#####################################################################################
// END HEALTH HELP MONITOR / BEGIN POLICE HELP MONITOR
//#####################################################################################
//#####################################################################################

:BUSTED_LOOP
wait 0 ms
if
	0256:   is_player $PLAYER_CHAR defined
then
	if and
		0121:   player $PLAYER_CHAR in_zone 'S_VIEW'  // Portland View
		0038:   $ONMISSION == 0
		0038:   $WANTED_INFO_TRIP == 1
		0214:   pickup $BUSTED_INFO_PICKUP picked_up
	then
		01B4: set_player $PLAYER_CHAR controllable 0 
		while fading
			wait 0 ms
		end
		0417: start_mission M02_POLICEHELP
		end_thread
	end
end
goto @BUSTED_LOOP

//#####################################################################################
//#####################################################################################
// END POLICE HELP MONITOR / BEGIN RC TRIGGERS
//#####################################################################################
//#####################################################################################

:RC_LOOP
wait 0 ms
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $JUST_DONE_RC_MISSION == 0
	0038:   $ONMISSION == 0
then
	if
		00DE:   is_player_in_model $PLAYER_CHAR model #TOYZ
	then
		if
			00F5:   player $PLAYER_CHAR 0 1014.0 -120.0 5.0 radius 5.0 5.0 5.0
		then
       	        	00BA: text_styled 'RC1' duration 5000 ms flag 2   // 'DIABLO DESTRUCTION'
      			        0417: start_mission M03_DIABLODESTRUCTION
		end
		if
			00F5:   player $PLAYER_CHAR 0 1158.0 -309.0 23.0 radius 5.0 5.0 5.0
		then
			00BA: text_styled 'RC2' duration 5000 ms flag 2   // 'MAFIA MASSACRE'
			0417: start_mission M04_MAFIAMASSACRE
		end
		if
			00F5:   player $PLAYER_CHAR 0 -636.0 65.0 19.0 radius 5.0 5.0 5.0
		then
			00BA: text_styled 'RC4' duration 5000 ms flag 2   // 'RUMPO RAMPAGE'
			0417: start_mission M05_RUMPORAMPAGE
		end
		if
			00F5:   player $PLAYER_CHAR 0 366.0 -1312.0 26.0 radius 5.0 5.0 5.0
		then
			00BA: text_styled 'RC3' duration 5000 ms flag 2   // 'CASINO CALAMITY'
			0417: start_mission M06_CASINOCALAMITY
		end
	end
end
if
	80DE:   not is_player_in_model $PLAYER_CHAR model #TOYZ
then
	0004:   $JUST_DONE_RC_MISSION = 0
end
goto @RC_LOOP

//#####################################################################################
//#####################################################################################
// END RC TRIGGERS / BEGIN 4x4 MISSION MONITORS
//#####################################################################################
//#####################################################################################

:4x4_MISSION1_LOOP
wait $WAIT_TIME ms
if
	0038:   $PATRIOT_PLAYGROUND_COMPLETED == 0
then
	0004:   $PATRIOT_PLAYGROUND_BEST_TIME = 300
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0 
then
	if
		0121:   player $PLAYER_CHAR in_zone 'S_VIEW'  // Portland View
	then
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #PATRIOT 
			8056:   not is_player_in_area_2d $PLAYER_CHAR coords 1294.0 -656.0 1316.0 -638.0 sphere 0 			
		then
			0004: $ON_PATRIOT_PLAYGROUND_MISSION = 1
		end
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #PATRIOT 
			0038:   $ON_PATRIOT_PLAYGROUND_MISSION == 0 
		then
			00BA: text_styled 'T4X4_1' duration 5000 ms flag 2  // 'PATRIOT PLAYGROUND'
			wait 0 ms
			0417: start_mission M07_PATRIOTPLAYGROUND
		end
		if
			0256:   is_player $PLAYER_CHAR defined
		then
			if
				80E0:   not player $PLAYER_CHAR driving
			then
				0004:   $ON_PATRIOT_PLAYGROUND_MISSION = 0
			end
		end
	end
end
goto @4x4_MISSION1_LOOP


:4x4_MISSION2_LOOP
wait $WAIT_TIME ms
if
	0038:   $A_RIDE_IN_THE_PARK_COMPLETED == 0
then
	0004:   $A_RIDE_IN_THE_PARK_BEST_TIME = 300
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0 
then
	if
		0121:   player $PLAYER_CHAR in_zone 'PARK'  // Belleville Park
	then
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #LANDSTAL 
			8056:   not is_player_in_area_2d $PLAYER_CHAR coords 58.0 -585.0 68.0 -595.0 sphere 0   			
		then
			0004: $ON_A_RIDE_IN_THE_PARK_MISSION = 1
		end
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #LANDSTAL 
			0038:   $ON_A_RIDE_IN_THE_PARK_MISSION == 0 
		then
			00BA: text_styled 'T4X4_2' duration 5000 ms flag 2   // 'A Ride In The Park'
			wait 0 ms
			0417: start_mission M08_ARIDEINTHEPARK
		end
		if
			0256:   is_player $PLAYER_CHAR defined
		then
			if
				80E0:   not player $PLAYER_CHAR driving
			then
				0004:   $ON_A_RIDE_IN_THE_PARK_MISSION = 0
			end
		end
	end
end
goto @4x4_MISSION2_LOOP


:4x4_MISSION3_LOOP
wait $WAIT_TIME ms
if
	0038:   $GRIPPED_COMPLETED == 0
then
	0004:   $GRIPPED_BEST_TIME = 300
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0 
then
	if
		0056:   is_player_in_area_2d $PLAYER_CHAR coords -230.0 255.0 -210.0 275.0 sphere 0 
	then
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #PATRIOT 
			8056:   not is_player_in_area_2d $PLAYER_CHAR coords -230.0 255.0 -210.0 275.0 sphere 0 			
		then
			0004: $ON_GRIPPED_MISSION = 1
		end
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #PATRIOT 
			0038:   $ON_GRIPPED_MISSION == 0
		then
			00BA: text_styled 'T4X4_3' duration 5000 ms flag 2   // 'GRIPPED!'
			wait 0 ms
			0417: start_mission M09_GRIPPED
		end
		if
			0256:   is_player $PLAYER_CHAR defined
		then
			if
				80E0:   not player $PLAYER_CHAR driving
			then
				0004:   $ON_GRIPPED_MISSION = 0
			end
		end
	end
end
goto @4x4_MISSION3_LOOP

:4x4_MISSION4_LOOP
wait $WAIT_TIME ms
if
	0038:   $MULTISTOREY_MAYHEM_COMPLETED == 0
then
	0004:   $MULTISTOREY_MAYHEM_BEST_TIME = 300
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0 
then
	if
		0121:   player $PLAYER_CHAR in_zone 'COM_EAS'  // Newport
	then
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #STALLION 
			8056:   not is_player_in_area_2d $PLAYER_CHAR coords 238.0 -612.0 267.0 -469.0 sphere 0  			
		then
			0004: $ON_MULTISTOREY_MAYHEM_MISSION = 1
		end
		if and
			00DE:   is_player_in_model $PLAYER_CHAR model #STALLION 
			0038:   $ON_MULTISTOREY_MAYHEM_MISSION == 0
		then
			00BA: text_styled 'MM_1' duration 5000 ms flag 2   // 'MULTISTOREY MAYHEM'
			wait 0 ms
			0417: start_mission M10_MULTISTOREYMAYHEM
		end
		if
			0256:   is_player $PLAYER_CHAR defined
		then
			if
				80E0:   not player $PLAYER_CHAR driving
			then
				0004:   $ON_MULTISTOREY_MAYHEM_MISSION = 0
			end
		end
	end
end
goto @4x4_MISSION4_LOOP

//#####################################################################################
//#####################################################################################
// END 4x4 MISSION MONITORS / BEGIN PARAMEDIC MONITOR
//#####################################################################################
//#####################################################################################

:PARAMEDIC_LOOP
wait 0 ms
if
	0256:   is_player $PLAYER_CHAR defined
then
	if
		00DE:   is_player_in_model $PLAYER_CHAR model #AMBULAN
	then
		if and
			0038:   $ON_PARAMEDIC_MISSION == 0
			0038:   $ONMISSION == 0
		then
			if
				0038:   $DISPLAYED_PARAMEDIC_HELP == 0 
			then
				03E5: text_box 'ATUTOR'  // Press the ~h~~k~~TOGGLE_SUBMISSIONS~ button~w~ to toggle Paramedic missions on or off.
				0004: $DISPLAYED_PARAMEDIC_HELP = 1
			end
			0293: $CONTROLMODE = current_controls
			if
				8038: NOT $CONTROLMODE == 3
			then
				if 
					00E1:   is_button_pressed PAD1 button RIGHTSHOCK
				then
					while 00E1:   is_button_pressed PAD1 button RIGHTSHOCK
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @PARAMEDIC_LOOP
						end
					end //while
					00BA: text_styled 'AMBUL_M' duration 4000 ms flag 5  // 'PARAMEDIC'
					wait 0 ms
					0417: start_mission M11_PARAMEDIC
					0004: $DISPLAYED_PARAMEDIC_HELP = 1
				end
			else
				if 
					00E1:   is_button_pressed PAD1 button SQUARE
				then
					while 00E1:   is_button_pressed PAD1 button SQUARE
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @PARAMEDIC_LOOP
						end
					end //while
					00BA: text_styled 'AMBUL_M' duration 4000 ms flag 5  // 'PARAMEDIC'
					wait 0 ms
					0417: start_mission M11_PARAMEDIC
					0004: $DISPLAYED_PARAMEDIC_HELP = 1
				end
			end
		end
	else
		if
			0038:   $DISPLAYED_PARAMEDIC_HELP == 1
		then
			03E6: remove_text_box
			0004: $DISPLAYED_PARAMEDIC_HELP = 0
		end
	end
end
goto @PARAMEDIC_LOOP

//#####################################################################################
//#####################################################################################
// END PARAMEDIC MONITOR / BEGIN FIRE MONITOR
//#####################################################################################
//#####################################################################################

:FIRE_LOOP
wait 0 ms
if
	0256:   is_player $PLAYER_CHAR defined
then
	if
		00DE:   is_player_in_model $PLAYER_CHAR model #FIRETRUK
	then
		if and
			0038:   $ON_FIREFIGHTER_MISSION == 0
			0038:   $ONMISSION == 0
		then
			if
				0038:   $FIRETRUCK_HELP_DISPLAYED == 0 
			then
				03E5: text_box 'FTUTOR'  // Press the ~h~~k~~TOGGLE_SUBMISSIONS~ button~w~ to toggle fire truck missions on or off.
				0004: $FIRETRUCK_HELP_DISPLAYED = 1
			end
			0293: $CONTROLMODE = current_controls
			if
				8038: NOT $CONTROLMODE == 3
			then
				if 
					00E1:   is_button_pressed PAD1 button RIGHTSHOCK
				then
					while 00E1:   is_button_pressed PAD1 button RIGHTSHOCK
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @FIRE_LOOP
						end
					end //while
					00BA: text_styled 'FIRE_M' duration 4000 ms flag 5  // 'FIREFIGHTER'
					wait 0 ms
					0417: start_mission M12_FIREFIGHTER
					0004: $FIRETRUCK_HELP_DISPLAYED = 1
				end
			else
				if 
					00E1:   is_button_pressed PAD1 button SQUARE
				then
					while 00E1:   is_button_pressed PAD1 button SQUARE
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @FIRE_LOOP
						end
					end //while
					00BA: text_styled 'FIRE_M' duration 4000 ms flag 5  // 'FIREFIGHTER'
					wait 0 ms
					0417: start_mission M12_FIREFIGHTER
					0004: $FIRETRUCK_HELP_DISPLAYED = 1
				end
			end
		end
	else
		if
			0038:   $FIRETRUCK_HELP_DISPLAYED == 1
		then
			03E6: remove_text_box
			0004: $FIRETRUCK_HELP_DISPLAYED = 0
		end
	end
end
goto @FIRE_LOOP

//#####################################################################################
//#####################################################################################
// END FIRE MONITOR / BEGIN VIGILANTE MONITOR
//#####################################################################################
//#####################################################################################

:VIGILANTE_LOOP
wait 0 ms
if
	0256:   is_player $PLAYER_CHAR defined
then
	if or
		00DE:   is_player_in_model $PLAYER_CHAR model #POLICE
		00DE:   is_player_in_model $PLAYER_CHAR model #ENFORCER
		00DE:   is_player_in_model $PLAYER_CHAR model #RHINO
		00DE:   is_player_in_model $PLAYER_CHAR model #FBICAR
	then
		if and
			0038:   $ON_VIGILANTE_MISSION == 0
			0038:   $ONMISSION == 0
		then
			if
				0038:   $VIGILANTE_HELP_DISPLAYED == 0 
			then
				03E5: text_box 'CTUTOR'  // Press the ~h~~k~~TOGGLE_SUBMISSIONS~ button~w~ to toggle Vigilante missions on or off.
				0004: $VIGILANTE_HELP_DISPLAYED = 1
			end
			0293: $CONTROLMODE = current_controls
			if
				8038: NOT $CONTROLMODE == 3
			then
				if 
					00E1:   is_button_pressed PAD1 button RIGHTSHOCK
				then
					while 00E1:   is_button_pressed PAD1 button RIGHTSHOCK
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @VIGILANTE_LOOP
						end
					end //while
					00BA: text_styled 'COP_M' duration 4000 ms flag 5   // 'VIGILANTE'
					wait 0 ms
					0417: start_mission M13_VIGILANTE
					0004: $VIGILANTE_HELP_DISPLAYED = 1
				end
			else
				if 
					00E1:   is_button_pressed PAD1 button SQUARE
				then
					while 00E1:   is_button_pressed PAD1 button SQUARE
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @VIGILANTE_LOOP
						end
					end //while
					00BA: text_styled 'COP_M' duration 4000 ms flag 5   // 'VIGILANTE'
					wait 0 ms
					0417: start_mission M13_VIGILANTE
					0004: $VIGILANTE_HELP_DISPLAYED = 1
				end
			end
		end
	else
		if
			0038:   $VIGILANTE_HELP_DISPLAYED == 1
		then
			03E6: remove_text_box
			0004: $VIGILANTE_HELP_DISPLAYED = 0
		end
	end
end
goto @VIGILANTE_LOOP

//#####################################################################################
//#####################################################################################
// END VIGILANTE MONITOR / BEGIN TAXI MONITOR
//#####################################################################################
//#####################################################################################

:TAXI_LOOP
wait 0 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		02DE:   player $PLAYER_CHAR driving_taxi_vehicle
	then
		if and
			0038:   $ONMISSION == 0
			0038:   $ON_TAXI_MISSION == 0
		then
			if
				0038:   $TAXI_HELP_DISPLAYED == 0 
			then
				03E5: text_box 'TTUTOR'  // Press the ~h~~k~~TOGGLE_SUBMISSIONS~ button~w~ to toggle taxi missions on or off.
				0004: $TAXI_HELP_DISPLAYED = 1
			end
			0293: $CONTROLMODE = get_controller_mode 
			if
				8038:   not  $CONTROLMODE == 3 
			then
				if 
					00E1:   is_button_pressed PAD1 button RIGHTSHOCK
				then
					while 00E1:   is_button_pressed PAD1 button RIGHTSHOCK
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @TAXI_LOOP
						end
					end //while
					00BA: text_styled 'TAXI_M' duration 4000 ms flag 5  // 'TAXI DRIVER'
					wait 0 ms
					0417: start_mission M14_TAXI
					0004: $ON_TAXI_MISSION = 1
				end
			else
				if 
					00E1:   is_button_pressed PAD1 button SQUARE
				then
					while 00E1:   is_button_pressed PAD1 button SQUARE
						wait 0 ms
						if
							8256:   not is_player $PLAYER_CHAR defined
						then
							goto @TAXI_LOOP
						end
					end //while
					00BA: text_styled 'TAXI_M' duration 4000 ms flag 5  // 'TAXI DRIVER'
					wait 0 ms
					0417: start_mission M14_TAXI
					0004: $ON_TAXI_MISSION = 1
				end
			end
		end
	else
		if
			0038:   $TAXI_HELP_DISPLAYED == 1
		then
			03E6: remove_text_box
			0004: $TAXI_HELP_DISPLAYED = 0
		end
	end
end
goto @TAXI_LOOP

//#####################################################################################
//#####################################################################################
// END TAXI MONITOR / BEGIN MARTY MONITORS
//#####################################################################################
//#####################################################################################

:MEAT_MISSION1_LOOP
wait $WAIT_TIME ms
if
	0038:   $THE_CROOK_COMPLETED == 1
then
	024E: disable_phone $PHONE_MARTY 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
	if and
		0028:   $CURRENT_TIME_HOURS >= 9 
		001A:   19 > $CURRENT_TIME_HOURS
	then
		0405: enable_phone $PHONE_MARTY
	else
		024E: disable_phone $PHONE_MARTY
	end
else
	024E: disable_phone $PHONE_MARTY
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_MARTY 
			00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
			if and
				0028:   $CURRENT_TIME_HOURS >= 9 
				001A:   19 > $CURRENT_TIME_HOURS
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				00BA: print_big 'MEA1' duration 15000 ms style 2  // 'THE CROOK'
				while fading
					wait 0 ms
				end
				0417: start_mission M15_THECROOK
			end
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @MEAT_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @MEAT_MISSION1_LOOP
			end
		end //while
	end
end
goto @MEAT_MISSION1_LOOP


:MEAT_MISSION2_LOOP
wait $WAIT_TIME ms
if
	0038:   $THE_THIEVES_COMPLETED == 1
then
	024E: disable_phone $PHONE_MARTY 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
	if and
		0028:   $CURRENT_TIME_HOURS >= 9 
		001A:   19 > $CURRENT_TIME_HOURS
	then
		0405: enable_phone $PHONE_MARTY
	else
		024E: disable_phone $PHONE_MARTY
	end
else
	024E: disable_phone $PHONE_MARTY
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_MARTY 
			00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
			if and
				0028:   $CURRENT_TIME_HOURS >= 9 
				001A:   19 > $CURRENT_TIME_HOURS
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				00BA: print_big 'MEA2' duration 15000 ms style 2  // 'THE THIEVES'
				while fading
					wait 0 ms
				end
				0417: start_mission M16_THETHIEVES
			end
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @MEAT_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @MEAT_MISSION2_LOOP
			end
		end //while
	end
end
goto @MEAT_MISSION2_LOOP


:MEAT_MISSION3_LOOP
wait $WAIT_TIME ms
if
	0038:   $THE_WIFE_COMPLETED == 1
then
	024E: disable_phone $PHONE_MARTY 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
	if and
		0028:   $CURRENT_TIME_HOURS >= 9 
		001A:   19 > $CURRENT_TIME_HOURS
	then
		0405: enable_phone $PHONE_MARTY
	else
		024E: disable_phone $PHONE_MARTY
	end
else
	024E: disable_phone $PHONE_MARTY
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_MARTY 
			00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
			if and
				0028:   $CURRENT_TIME_HOURS >= 9 
				001A:   19 > $CURRENT_TIME_HOURS
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				00BA: print_big 'MEA3' duration 15000 ms style 2  // 'THE WIFE'
				while fading
					wait 0 ms
				end
				0417: start_mission M17_THEWIFE
			end
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @MEAT_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @MEAT_MISSION3_LOOP
			end
		end //while
	end
end
goto @MEAT_MISSION3_LOOP


:MEAT_MISSION4_LOOP
wait $WAIT_TIME ms
if
	0038:   $HER_LOVER_COMPLETED == 1
then
	024E: disable_phone $PHONE_MARTY 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
	if and
		0028:   $CURRENT_TIME_HOURS >= 9 
		001A:   19 > $CURRENT_TIME_HOURS
	then
		0405: enable_phone $PHONE_MARTY
	else
		024E: disable_phone $PHONE_MARTY
	end
else
	024E: disable_phone $PHONE_MARTY
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_MARTY 
			00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
			if and
				0028:   $CURRENT_TIME_HOURS >= 9 
				001A:   19 > $CURRENT_TIME_HOURS
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				00BA: print_big 'MEA4' duration 15000 ms style 2  // 'HER LOVER'
				while fading
					wait 0 ms
				end
				0417: start_mission M18_HERLOVER
			end
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @MEAT_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1224.563 -840.25 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @MEAT_MISSION4_LOOP
			end
		end //while
	end
end
goto @MEAT_MISSION4_LOOP


//#####################################################################################
//#####################################################################################
// END MARTY MONITORS / BEGIN EIGHT MONITOR
//#####################################################################################
//#####################################################################################

:EIGHTBALL_MISSION_LOOP
wait 0 ms
if or
    0038:   $FLAG_STAUNTON_OPEN == 1  
    0038:   $LUIGIS_GIRLS_COMPLETED == 1  
then
    end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $FLAG_EIGHTBALL_MISSION_LAUNCHED == 0 
	0038:   $ONMISSION == 0 
then
	if
		0038:   $FLAG_REACHED_HIDEOUT == 0 
	then
		if and
			00E4:   player $PLAYER_CHAR 0 811.875 -939.9375 radius 3.5 3.5
			03EE:   player $PLAYER_CHAR controllable 
		then
			0417: start_mission M19_GIVEMELIBERTY
			0004: $FLAG_EIGHTBALL_MISSION_LAUNCHED = 1
		end
	else // PLAYER REACHED HIDEOUT
		if and
			00E4:   player $PLAYER_CHAR 0 883.5 -308.1875 radius 3.5 3.5 
			03EE:   player $PLAYER_CHAR controllable 
		then
			0417: start_mission M19_GIVEMELIBERTY
			0004: $FLAG_EIGHTBALL_MISSION_LAUNCHED = 1 
		end
	end
end
goto @EIGHTBALL_MISSION_LOOP

//#####################################################################################
//#####################################################################################
// END EIGHT MONITOR / BEGIN LUIGI MONITORS
//#####################################################################################
//#####################################################################################

:LUIGI_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $DONT_SPANK_MA_BITCH_UP_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LM2' duration 15000 ms style 2  // 'DON'T SPANK MA BITCH UP'
			while fading
				wait 0 ms
			end
			0417: start_mission M20_DONTSPANKMABITCHUP
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LUIGI_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LUIGI_MISSION2_LOOP
			end
		end //while
	end
end
goto @LUIGI_MISSION2_LOOP


:LUIGI_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $DRIVE_MISTY_FOR_ME_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LM3' duration 15000 ms style 2  // 'DRIVE MISTY FOR ME'
			while fading
				wait 0 ms
			end
			0417: start_mission M21_DRIVEMISTYFORME
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LUIGI_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LUIGI_MISSION3_LOOP
			end
		end //while
	end
end
goto @LUIGI_MISSION3_LOOP


:LUIGI_MISSION4_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $PUMP_ACTION_PIMP_COMPLETED == 1
then
	end_thread
end
if and
	0038:   $SALVATORES_CALLED_A_MEETING_COMPLETED == 1 
	0038:   $CHAPERONE_COMPLETED == 0
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LM4' duration 15000 ms style 2  // 'PUMP-ACTION PIMP'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M22_PUMPACTIONPIMP ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LUIGI_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LUIGI_MISSION4_LOOP
			end
		end //while
	end
end
goto @LUIGI_MISSION4_LOOP

:LUIGI_MISSION5_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $FUZZ_BALL_COMPLETED == 1
then
	end_thread
end
if and
	0038:   $SALVATORES_CALLED_A_MEETING_COMPLETED == 1 
	0038:   $CHAPERONE_COMPLETED == 0
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LM5' duration 15000 ms style 2  // 'THE FUZZ BALL'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M23_THEFUZZBALL ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LUIGI_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 892.75 -425.75 13.875 radius 1.5 2.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LUIGI_MISSION5_LOOP
			end
		end //while
	end
end
goto @LUIGI_MISSION5_LOOP

//#####################################################################################
//#####################################################################################
// END LUIGI MONITORS / BEGIN JOEY MONITORS
//#####################################################################################
//#####################################################################################

:JOEY_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $MIKE_LIPS_LAST_LUNCH_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
	then
		00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
		if and
			0028:   $CURRENT_TIME_HOURS >= 5
			001A:   21 > $CURRENT_TIME_HOURS
		then
			if
				03EE:   player $PLAYER_CHAR controllable 
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				03AF: set_streaming 0 
				00BA: print_big 'JM1' duration 15000 ms style 2  // 'MIKE LIPS LAST LUNCH'
				while fading
					wait 0 ms
				end
				0417: start_mission M24_MIKELIPSLASTLUNCH
			end
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION1_LOOP
			end
			while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
				wait 0 ms
				if
					8256:   not is_player $PLAYER_CHAR defined
				then
					goto @JOEY_MISSION1_LOOP
				end
			end //while
		else
			00BC: print_now 'WRONGT1' duration 5000 ms flag 1  // ~g~Come back between 05:00 and 21:00 for a job
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION1_LOOP
			end
			00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
			if and
				0028:   $CURRENT_TIME_HOURS >= 5
				001A:   21 > $CURRENT_TIME_HOURS
			then
				goto @JOEY_MISSION1_LOOP
			end
		end //while
	end
end
goto @JOEY_MISSION1_LOOP

:JOEY_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $FAREWELL_CHUNKY_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'JM2' duration 15000 ms style 2  // 'FAREWELL 'CHUNKY' LEE CHONG'
			while fading
				wait 0 ms
			end
			0417: start_mission M25_FAREWELLCHUNKYLEECHONG
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @JOEY_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION2_LOOP
			end
		end //while
	end
end
goto @JOEY_MISSION2_LOOP

:JOEY_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $VAN_HEIST_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'JM3' duration 15000 ms style 2  // 'VAN HEIST'
			while fading
				wait 0 ms
			end
			0417: start_mission M26_VANHEIST
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @JOEY_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION3_LOOP
			end
		end //while
	end
end
goto @JOEY_MISSION3_LOOP

:JOEY_MISSION4_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $CIPRIANIS_CHAUFFEUR_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'JM4' duration 15000 ms style 2  // 'CIPRIANI'S CHAUFFEUR'
			while fading
				wait 0 ms
			end
			0417: start_mission M27_CIPRIANISCHAUFFEUR
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @JOEY_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION4_LOOP
			end
		end //while
	end
end
goto @JOEY_MISSION4_LOOP

:JOEY_MISSION5_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $DEAD_SKUNK_IN_THE_TRUNK_COMPLETED == 1
then
	end_thread
end
if and
	0038:   $SALVATORES_CALLED_A_MEETING_COMPLETED == 1 
	0038:   $CHAPERONE_COMPLETED == 0 
then
	0004: $FLAG_JOEY_MISSION5_TERMINATED = 1 
	end_thread
end	
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'JM5' duration 15000 ms style 2  // 'DEAD SKUNK IN THE TRUNK'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M28_DEADSKUNKINTHETRUNK ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @JOEY_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION5_LOOP
			end
		end //while
	end
end
goto @JOEY_MISSION5_LOOP

:JOEY_MISSION6_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $THE_GETAWAY_COMPLETED == 1
then
	end_thread
end
if and
	0038:   $SALVATORES_CALLED_A_MEETING_COMPLETED == 1 
	0038:   $CHAPERONE_COMPLETED == 0 
then
	0004: $FLAG_JOEY_MISSION6_TERMINATED = 1 
	end_thread
end	
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
	then
		00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
		if and
			0028:   $CURRENT_TIME_HOURS >= 6
			001A:   15 > $CURRENT_TIME_HOURS
		then
			if
				03EE:   player $PLAYER_CHAR controllable 
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				03AF: set_streaming 0 
				00BA: print_big 'JM6' duration 15000 ms style 2  // 'THE GETAWAY'
				while fading
					wait 0 ms
				end
				// 0417: start_mission M29_THEGETAWAY ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			end
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION6_LOOP
			end
			while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
				wait 0 ms
				if
					8256:   not is_player $PLAYER_CHAR defined
				then
					goto @JOEY_MISSION6_LOOP
				end
			end //while
		else
			00BC: print_now 'WRONGT2' duration 5000 ms flag 1  // ~g~Come back between 06:00 and 14:00 for a job
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @JOEY_MISSION6_LOOP
			end
			00BF: get_time_of_day $CURRENT_TIME_HOURS $CURRENT_TIME_MINUTES
			if and
				0028:   $CURRENT_TIME_HOURS >= 6
				001A:   14 > $CURRENT_TIME_HOURS
			then
				goto @JOEY_MISSION6_LOOP
			end
		end //while
	end
end
goto @JOEY_MISSION6_LOOP

//#####################################################################################
//#####################################################################################
// END JOEY MONITORS / BEGIN TONI MONITORS
//#####################################################################################
//#####################################################################################

:TONI_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $TAKING_OUT_THE_LAUNDRY_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1219.75 -319.6875 27.375 radius 1.0 2.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'TM1' duration 15000 ms style 2  // 'TAKING OUT THE LAUNDRY'
			while fading
				wait 0 ms
			end
			0417: start_mission M30_TAKINGOUTTHELAUNDRY
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @TONI_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @TONI_MISSION1_LOOP
			end
		end //while
	end
end
goto @TONI_MISSION1_LOOP

:TONI_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $THE_PICKUP_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1219.75 -319.6875 27.375 radius 1.0 2.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'TM2' duration 15000 ms style 2  // 'THE PICK-UP'
			while fading
				wait 0 ms
			end
			0417: start_mission M31_THEPICKUP
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @TONI_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @TONI_MISSION2_LOOP
			end
		end //while
	end
end
goto @TONI_MISSION2_LOOP

:TONI_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $SALVATORES_CALLED_A_MEETING_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1219.75 -319.6875 27.375 radius 1.0 2.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'TM3' duration 15000 ms style 2  // 'SALVATORE'S CALLED A MEETING'
			while fading
				wait 0 ms
			end
			0417: start_mission M32_SALVATORESCALLEDAMEETING
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @TONI_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @TONI_MISSION3_LOOP
			end
		end //while
	end
end
goto @TONI_MISSION3_LOOP

:TONI_MISSION4_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $TRIADS_AND_TRIBULATIONS_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1219.75 -319.6875 27.375 radius 1.0 2.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'TM4' duration 15000 ms style 2  // 'TRIADS AND TRIBULATIONS'
			while fading
				wait 0 ms
			end
			0417: start_mission M33_TRIADSANDTRIBULATIONS
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @TONI_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @TONI_MISSION4_LOOP
			end
		end //while
	end
end
goto @TONI_MISSION4_LOOP

:TONI_MISSION5_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $BLOW_FISH_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 1219.75 -319.6875 27.375 radius 1.0 2.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'TM5' duration 15000 ms style 2  // 'BLOW FISH'
			while fading
				wait 0 ms
			end
			0417: start_mission M34_BLOWFISH
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @TONI_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1191.688 -870.0 15.0 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @TONI_MISSION5_LOOP
			end
		end //while
	end
end
goto @TONI_MISSION5_LOOP

//#####################################################################################
//#####################################################################################
// END TONI MONITORS / BEGIN SALVATORE MONITORS
//#####################################################################################
//#####################################################################################

:FRANKIE_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $CHAPERONE_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
	0038:   $FLAG_FRANKIE_SWITCHED_OFF == 0
then
	if or
		00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
		02B4:   player $PLAYER_CHAR in_cube_on_foot 1466.188 -175.0 50.0 1452.875 -172.0625 60.0 radius 11.5625 sphere 0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'FM1' duration 15000 ms style 2  // 'CHAPERONE'
			while fading
				wait 0 ms
			end
			0417: start_mission M35_CHAPERONE
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @FRANKIE_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @FRANKIE_MISSION1_LOOP
			end
		end //while
	end
end
goto @FRANKIE_MISSION1_LOOP


:FRANKIE_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $CUTTING_THE_GRASS_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
	0038:   $FLAG_FRANKIE_SWITCHED_OFF == 0
then
	if or
		00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
		02B4:   player $PLAYER_CHAR in_cube_on_foot 1466.188 -175.0 50.0 1452.875 -172.0625 60.0 radius 11.5625 sphere 0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'FM2' duration 15000 ms style 2  // 'CUTTING THE GRASS'
			while fading
				wait 0 ms
			end
			0417: start_mission M36_CUTTINGTHEGRASS
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @FRANKIE_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @FRANKIE_MISSION2_LOOP
			end
		end //while
	end
end
goto @FRANKIE_MISSION2_LOOP


:FRANKIE_MISSION21_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $BOMB_DA_BASE_ACT_I_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 

then
	if or
		00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
		02B4:   player $PLAYER_CHAR in_cube_on_foot 1466.188 -175.0 50.0 1452.875 -172.0625 60.0 radius 11.5625 sphere 0
	then
		if
			0038:   $FLAG_FRANKIE_SWITCHED_OFF == 0
		then
			if
				03EE:   player $PLAYER_CHAR controllable 
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				03AF: set_streaming 0 
				00BA: print_big 'FM21' duration 15000 ms style 2  // 'BOMB DA BASE: ACT I'
				while fading
					wait 0 ms
				end
				0417: start_mission M37_BOMBDABASEACTI
			end
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @FRANKIE_MISSION21_LOOP
			end
			while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
				wait 0 ms
				if
					8256:   not is_player $PLAYER_CHAR defined
				then
					goto @FRANKIE_MISSION21_LOOP
				end
			end //while
		end
		00BC: print_now 'FRANGO' duration 5000 ms flag 1  // ~g~Salvatore wants you to help Toni deal with the Triads first!
		while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @FRANKIE_MISSION21_LOOP
			end
		end //while
	end
end
goto @FRANKIE_MISSION21_LOOP


:FRANKIE_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $BOMB_DA_BASE_ACT_II_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F6:   player $PLAYER_CHAR 0 1272.188 -92.875 13.75 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'FM3' duration 15000 ms style 2  // 'BOMB DA BASE: ACT II'
			while fading
				wait 0 ms
			end
			0417: start_mission M38_BOMBDABASEACTII
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @FRANKIE_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1272.188 -92.875 13.75 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @FRANKIE_MISSION3_LOOP
			end
		end //while
	end
end
goto @FRANKIE_MISSION3_LOOP


:FRANKIE_MISSION4_LOOP
0001: wait $WAIT_TIME ms
if or
	0038:   $FLAG_STAUNTON_OPEN == 1 
	0038:   $LAST_REQUESTS_COMPLETED == 1
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
	0038:   $FLAG_FRANKIE_SWITCHED_OFF == 0
then
	if or
		00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
		02B4:   player $PLAYER_CHAR in_cube_on_foot 1466.188 -175.0 50.0 1452.875 -172.0625 60.0 radius 11.5625 sphere 0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'FM4' duration 15000 ms style 2  // 'LAST REQUESTS'
			while fading
				wait 0 ms
			end
			0417: start_mission M39_LASTREQUESTS
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @FRANKIE_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @FRANKIE_MISSION4_LOOP
			end
		end //while
	end
end
goto @FRANKIE_MISSION4_LOOP


//#####################################################################################
//#####################################################################################
// END SALVATORE TRIGGERS / BEGIN DIABLO MONITORS
//#####################################################################################
//#####################################################################################

:DIABLO_PHONE_START
03A4: name_thread 'DIAB_PH' 
wait 10000 ms
if and
	0256:   is_player $PLAYER_CHAR defined
	03C6:   current_island == 1
	0038:   $ONMISSION == 0
then
	014D: text_pager 'DIAB1_A' 140 2 0  // El Burro wants to offer you an opportunity. Get to the payphone in Hepburn Heights if you want more info.
	004F: create_thread @DIABLO_MISSION1_LOOP 
	004F: create_thread @DIABLO_RADAR 
	004E: end_thread
end
goto @DIABLO_PHONE_START

:DIABLO_RADAR
03A4: name_thread 'DIAB_BP'
wait 1000 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		03C6:   current_island == 1
	then
		if
			0038:   $DIABLOS_RADAR_DISPLAYED == 0
		then
			0164: disable_marker $DIABLO_PHONE_MARKER 
			02A7: $DIABLO_PHONE_MARKER = create_icon_marker_and_sphere RADAR_SPRITE_DIABLO at 938.375 -230.5 -100.0 
			0004: $DIABLOS_RADAR_DISPLAYED = 1
		end
	else
		if
			0038:   $DIABLOS_RADAR_DISPLAYED == 1
		then
			0164: disable_marker $DIABLO_PHONE_MARKER 
			0004: $DIABLOS_RADAR_DISPLAYED = 0
		end
	end
end
goto @DIABLO_RADAR

:DIABLO_MISSION1_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $TURISMO_COMPLETED == 1
then
	024E: disable_phone $PHONE_DIABLOS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_DIABLOS
else
	024E: disable_phone $PHONE_DIABLOS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_DIABLOS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'DIAB1' duration 15000 ms style 2  // 'TURISMO'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M40_TURISMO ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @DIABLO_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @DIABLO_MISSION1_LOOP
			end
		end //while
	end
end
goto @DIABLO_MISSION1_LOOP

:DIABLO_MISSION2_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $I_SCREAM_YOU_SCREAM_COMPLETED == 1
then
	024E: disable_phone $PHONE_DIABLOS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_DIABLOS
else
	024E: disable_phone $PHONE_DIABLOS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_DIABLOS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'DIAB2' duration 15000 ms style 2  // 'I SCREAM, YOU SCREAM'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M41_ISCREAMYOUSCREAM ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @DIABLO_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @DIABLO_MISSION2_LOOP
			end
		end //while
	end
end
goto @DIABLO_MISSION2_LOOP

:DIABLO_MISSION3_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $TRIAL_BY_FIRE_COMPLETED == 1
then
	024E: disable_phone $PHONE_DIABLOS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_DIABLOS
else
	024E: disable_phone $PHONE_DIABLOS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_DIABLOS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'DIAB3' duration 15000 ms style 2  // 'TRIAL BY FIRE'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M42_TRIALBYFIRE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @DIABLO_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @DIABLO_MISSION3_LOOP
			end
		end //while
	end
end
goto @DIABLO_MISSION3_LOOP

:DIABLO_MISSION4_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $BIG_N_VEINY_COMPLETED == 1
then
	024E: disable_phone $PHONE_DIABLOS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_DIABLOS
else
	024E: disable_phone $PHONE_DIABLOS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_DIABLOS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'DIAB4' duration 15000 ms style 2  // 'BIG'N'VEINY'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M43_BIGNVEINY ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @DIABLO_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 938.375 -230.5 3.875 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @DIABLO_MISSION4_LOOP
			end
		end //while
	end
end
goto @DIABLO_MISSION4_LOOP

//#####################################################################################
//#####################################################################################
// END DIABLO MONITORS / BEGIN ASUKA1 MONITORS
//#####################################################################################
//#####################################################################################

:ASUKA_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $SAYONARA_SALVATORE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AM1' duration 15000 ms style 2  // 'SAYONARA SALVATORE'
			while fading
				wait 0 ms
			end
			0417: start_mission M44_SAYONARASALVATORE
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_MISSION1_LOOP
			end
		end //while
	end
end
goto @ASUKA_MISSION1_LOOP


:ASUKA_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $UNDER_SURVEILLANCE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AM2' duration 15000 ms style 2  // 'UNDER SURVEILLANCE'
			while fading
				wait 0 ms
			end
			0417: start_mission M45_UNDERSURVEILLANCE
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_MISSION2_LOOP
			end
		end //while
	end
end
goto @ASUKA_MISSION2_LOOP


:ASUKA_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $PAPARAZZI_PURGE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AM3' duration 15000 ms style 2  // 'PAPARAZZI PURGE'
			while fading
				wait 0 ms
			end
			0417: start_mission M46_PAPARAZZIPURGE
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_MISSION3_LOOP
			end
		end //while
	end
end
goto @ASUKA_MISSION3_LOOP


:ASUKA_MISSION4_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $PAYDAY_FOR_RAY_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AM4' duration 15000 ms style 2  // 'PAYDAY FOR RAY'
			while fading
				wait 0 ms
			end
			0417: start_mission M47_PAYDAYFORRAY
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_MISSION4_LOOP
			end
		end //while
	end
end
goto @ASUKA_MISSION4_LOOP


:ASUKA_MISSION5_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $GRAND_THEFT_AERO_COMPLETED == 1 
	0038:   $TWO_FACED_TANNER_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AM5' duration 15000 ms style 2  // 'TWO-FACED TANNER'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M48_TWOFACEDTANNER ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 523.6875 -639.0 16.0625 radius 1.0 4.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_MISSION5_LOOP
			end
		end //while
	end
end
goto @ASUKA_MISSION5_LOOP

//#####################################################################################
//#####################################################################################
// END ASUKA1 MONITORS / BEGIN KENJI MONITORS
//#####################################################################################
//#####################################################################################

:KENJI_MISSION1_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $KANBU_BUST_OUT_COMPLETED == 1 
	0038:   $WAKAGASHIRA_WIPEOUT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'KM1' duration 15000 ms style 2  // 'KANBU BUST-OUT'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M49_KANBUBUSTOUT ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @KENJI_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @KENJI_MISSION1_LOOP
			end
		end //while
	end
end
goto @KENJI_MISSION1_LOOP


:KENJI_MISSION2_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $GRAND_THEFT_AUTO_COMPLETED == 1 
	0038:   $WAKAGASHIRA_WIPEOUT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'KM2' duration 15000 ms style 2  // 'GRAND THEFT AUTO'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M50_GRANDTHEFTAUTO ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @KENJI_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @KENJI_MISSION2_LOOP
			end
		end //while
	end
end
goto @KENJI_MISSION2_LOOP


:KENJI_MISSION3_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $DEAL_STEAL_COMPLETED == 1 
	0038:   $WAKAGASHIRA_WIPEOUT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'KM3' duration 15000 ms style 2  // 'DEAL STEAL'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M51_DEALSTEAL ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @KENJI_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @KENJI_MISSION3_LOOP
			end
		end //while
	end
end
goto @KENJI_MISSION3_LOOP


:KENJI_MISSION4_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $SHIMA_COMPLETED == 1 
	0038:   $WAKAGASHIRA_WIPEOUT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'KM4' duration 15000 ms style 2  // 'SHIMA'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M52_SHIMA ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @KENJI_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @KENJI_MISSION4_LOOP
			end
		end //while
	end
end
goto @KENJI_MISSION4_LOOP


:KENJI_MISSION5_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if or
	0038:   $SMACK_DOWN_COMPLETED == 1 
	0038:   $WAKAGASHIRA_WIPEOUT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'KM5' duration 15000 ms style 2  // 'SMACK DOWN'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M53_SMACKDOWN ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @KENJI_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 459.0625 -1413.0 26.0625 radius 1.5 1.5 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @KENJI_MISSION5_LOOP
			end
		end //while
	end
end
goto @KENJI_MISSION5_LOOP


//#####################################################################################
//#####################################################################################
// END KENJI MONITORS / BEGIN RAY MONITORS
//#####################################################################################
//#####################################################################################

:RAY_MISSION1_LOOP
wait 0 ms
if
	0038:   $SILENCE_THE_SNEAK_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then	
			0004: $RAYS_CUTSCENE_FLAG = 1
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'RM1' duration 15000 ms style 2  // 'SILENCE THE SNEAK'
			while fading
				wait 0 ms
			end
			0417: start_mission M54_SILENCETHESNEAK
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @RAY_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @RAY_MISSION1_LOOP
			end
		end //while
	end
end
goto @RAY_MISSION1_LOOP


:RAY_MISSION2_LOOP
0001: wait 0 ms
if
	0038:   $ARMS_SHORTAGE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then	
			0004: $RAYS_CUTSCENE_FLAG = 1
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'RM2' duration 15000 ms style 2  // 'ARMS SHORTAGE'
			while fading
				wait 0 ms
			end
			0417: start_mission M55_ARMSSHORTAGE
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @RAY_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @RAY_MISSION2_LOOP
			end
		end //while
	end
end
goto @RAY_MISSION2_LOOP


:RAY_MISSION3_LOOP
0001: wait 0 ms
if
	0038:   $EVIDENCE_DASH_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then	
			0004: $RAYS_CUTSCENE_FLAG = 1
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'RM3' duration 15000 ms style 2  // 'EVIDENCE DASH'
			while fading
				wait 0 ms
			end
			0417: start_mission M56_EVIDENCEDASH
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @RAY_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @RAY_MISSION3_LOOP
			end
		end //while
	end
end
goto @RAY_MISSION3_LOOP


:RAY_MISSION4_LOOP
0001: wait 0 ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $GONE_FISHING_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then	
			0004: $RAYS_CUTSCENE_FLAG = 1
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'RM4' duration 15000 ms style 2  // 'GONE FISHING'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M57_GONEFISHING ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @RAY_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @RAY_MISSION4_LOOP
			end
		end //while
	end
end
goto @RAY_MISSION4_LOOP


:RAY_MISSION5_LOOP
0001: wait 0 ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $PLASTER_BLASTER_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then	
			0004: $RAYS_CUTSCENE_FLAG = 1
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'RM5' duration 15000 ms style 2  // 'PLASTER BLASTER'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M58_PLASTERBLASTER ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @RAY_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @RAY_MISSION5_LOOP
			end
		end //while
	end
end
goto @RAY_MISSION5_LOOP


:RAY_MISSION6_LOOP
0001: wait 0 ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $MARKED_MAN_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then	
			0004: $RAYS_CUTSCENE_FLAG = 1
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'RM6' duration 15000 ms style 2  // 'MARKED MAN'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M59_MARKEDMAN ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @RAY_MISSION6_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 38.75 -725.375 22.75 radius 1.1875 1.1875 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @RAY_MISSION6_LOOP
			end
		end //while
	end
end
goto @RAY_MISSION6_LOOP

//#####################################################################################
//#####################################################################################
// END RAY MONITORS / BEGIN DONALD LOVE1 MONITORS
//#####################################################################################
//#####################################################################################

:LOVE_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $LIBERATOR_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE1' duration 15000 ms style 2  // 'LIBERATOR'
			while fading
				wait 0 ms
			end
			0417: start_mission M60_LIBERATOR
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION1_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION1_LOOP


:LOVE_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $WAKAGASHIRA_WIPEOUT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE2' duration 15000 ms style 2  // 'WAKA-GASHIRA WIPEOUT!'
			while fading
				wait 0 ms
			end
			0417: start_mission M61_WAKAGASHIRAWIPEOUT
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION2_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION2_LOOP


:LOVE_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $A_DROP_IN_THE_OCEAN_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE3' duration 15000 ms style 2  // 'A DROP IN THE OCEAN'
			while fading
				wait 0 ms
			end
			0417: start_mission M62_ADROPINTHEOCEAN
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION3_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION3_LOOP

//#####################################################################################
//#####################################################################################
// END DONALD LOVE1 MONITORS / BEGIN YARDIE MONITORS
//#####################################################################################
//#####################################################################################

:YARDIE_PHONE_START
03A4: name_thread 'YARD_PH' 
wait 10000 ms
if and
	0256:   is_player $PLAYER_CHAR defined
	03C6:   current_island == 2
	0038:   $ONMISSION == 0
then
	014D: text_pager 'YD_P' 140 2 0  // King Courtney would like a word. Get to the payphone in Aspatria!!
	004F: create_thread @YARDIE_MISSION1_LOOP 
	004F: create_thread @YARDIE_RADAR 
	004E: end_thread
end
goto @YARDIE_PHONE_START

:YARDIE_RADAR
03A4: name_thread 'YARD_BP'
wait 1000 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		03C6:   current_island == 2
	then
		if
			0038:   $KINGDOM_COME_COMPLETED == 1 
		then
			end_thread
		end
		if
			0038:   $YARDIES_RADAR_DISPLAYED == 0
		then
			0164: disable_marker $YARDIE_PHONE_MARKER 
			02A7: $YARDIE_PHONE_MARKER = create_icon_marker_and_sphere RADAR_SPRITE_YARDIE at 120.6875 -272.0625 16.0625  
			0004: $YARDIES_RADAR_DISPLAYED = 1
		end
	else
		if
			0038:   $YARDIES_RADAR_DISPLAYED == 1
		then
			0164: disable_marker $YARDIE_PHONE_MARKER 
			0004: $YARDIES_RADAR_DISPLAYED = 0
		end
	end
end
goto @YARDIE_RADAR

:YARDIE_MISSION1_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $BLING_BLING_SCRAMBLE_COMPLETED == 1
then
	024E: disable_phone $PHONE_YARDIES 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_YARDIES
else
	024E: disable_phone $PHONE_YARDIES
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_YARDIES 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'YD1' duration 15000 ms style 2  // 'BLING-BLING SCRAMBLE'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M63_BLINGBLINGSCRAMBLE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @YARDIE_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @YARDIE_MISSION1_LOOP
			end
		end //while
	end
end
goto @YARDIE_MISSION1_LOOP

:YARDIE_MISSION2_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $UZI_RIDER_COMPLETED == 1
then
	024E: disable_phone $PHONE_YARDIES 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_YARDIES
else
	024E: disable_phone $PHONE_YARDIES
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_YARDIES 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'YD2' duration 15000 ms style 2  // 'UZI RIDER'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M64_UZIRIDER ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @YARDIE_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @YARDIE_MISSION2_LOOP
			end
		end //while
	end
end
goto @YARDIE_MISSION2_LOOP


:YARDIE_MISSION3_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $GANGCAR_ROUNDUP_COMPLETED == 1
then
	024E: disable_phone $PHONE_YARDIES 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_YARDIES
else
	024E: disable_phone $PHONE_YARDIES
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_YARDIES 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'YD3' duration 15000 ms style 2  // 'GANGCAR ROUND-UP'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M65_GANGCARROUNDUP ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @YARDIE_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @YARDIE_MISSION3_LOOP
			end
		end //while
	end
end
goto @YARDIE_MISSION3_LOOP


:YARDIE_MISSION4_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $GANGCAR_ROUNDUP_COMPLETED == 1
then
	024E: disable_phone $PHONE_YARDIES 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_YARDIES
else
	024E: disable_phone $PHONE_YARDIES
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_YARDIES 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'YD4' duration 15000 ms style 2  // 'KINGDOM COME'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M66_KINGDOMCOME ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @YARDIE_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 120.6875 -272.0625 16.0625 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @YARDIE_MISSION4_LOOP
			end
		end //while
	end
end
goto @YARDIE_MISSION4_LOOP

//#####################################################################################
//#####################################################################################
// END YARDIE MONITORS / BEGIN DONALD LOVE2 MONITORS
//#####################################################################################
//#####################################################################################


:LOVE_MISSION4_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $GRAND_THEFT_AERO_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE4' duration 15000 ms style 2  // 'GRAND THEFT AERO'
			while fading
				wait 0 ms
			end
			0417: start_mission M67_GRANDTHEFTAERO
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION4_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION4_LOOP


:LOVE_MISSION5_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $ESCORT_SERVICE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE5' duration 15000 ms style 2  // 'ESCORT SERVICE'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M68_ESCORTSERVICE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION5_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION5_LOOP


:LOVE_MISSION6_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $DECOY_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE6' duration 15000 ms style 2  // 'DECOY'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M69_DECOY ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION6_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION6_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION6_LOOP


:LOVE_MISSION7_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $LOVES_DISAPPEARANCE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'LOVE7' duration 15000 ms style 2  // LOVE'S DISAPPEARANCE
			while fading
				wait 0 ms
			end
			// 0417: start_mission M70_LOVESDISAPPERANCE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @LOVE_MISSION7_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 87.25 -1548.563 28.25 radius 2.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @LOVE_MISSION7_LOOP
			end
		end //while
	end
end
goto @LOVE_MISSION7_LOOP


//#####################################################################################
//#####################################################################################
// END DONALD LOVE MONITORS / BEGIN ASUKA2 MONITORS
//#####################################################################################
//#####################################################################################

:ASUKA_SUBURBAN_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $BAIT_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 367.25 -328.0625 19.5 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AS1' duration 15000 ms style 2  // 'BAIT'
			while fading
				wait 0 ms
			end
			0417: start_mission M71_BAIT
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_SUBURBAN_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 367.25 -328.0625 19.5 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_SUBURBAN_MISSION1_LOOP
			end
		end //while
	end
end
goto @ASUKA_SUBURBAN_MISSION1_LOOP

:ASUKA_SUBURBAN_MISSION2_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $ESPRESSO_2_GO_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 367.25 -328.0625 19.5 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AS2' duration 15000 ms style 2  // 'ESPRESSO-2-GO!'
			while fading
				wait 0 ms
			end
			0417: start_mission M72_ESPRESSO2GO
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_SUBURBAN_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 367.25 -328.0625 19.5 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_SUBURBAN_MISSION2_LOOP
			end
		end //while
	end
end
goto @ASUKA_SUBURBAN_MISSION2_LOOP

:ASUKA_SUBURBAN_MISSION3_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $SAM_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 367.25 -328.0625 19.5 radius 1.0 1.0 2.0
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			03AF: set_streaming 0 
			00BA: print_big 'AS3' duration 15000 ms style 2  // 'S.A.M.'
			while fading
				wait 0 ms
			end
			0417: start_mission M73_SAM
		end
		if
			8256:   not is_player $PLAYER_CHAR defined
		then
			goto @ASUKA_SUBURBAN_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 367.25 -328.0625 19.5 radius 1.0 1.0 2.0
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @ASUKA_SUBURBAN_MISSION3_LOOP
			end
		end //while
	end
end
goto @ASUKA_SUBURBAN_MISSION3_LOOP


//#####################################################################################
//#####################################################################################
// END ASUKA2 MONITORS / BEGIN HOODS MONITORS
//#####################################################################################
//#####################################################################################

:HOODS_PHONE_START
03A4: name_thread 'HOOD_PH' 
wait 10000 ms
if and
	0256:   is_player $PLAYER_CHAR defined
	03C6:   current_island == 3
	0038:   $ONMISSION == 0
then
	014D: text_pager 'HOOD1_A' 140 2 0  // Get to the payphone in Wichita Gardens and we'll talk business.
	004F: create_thread @HOODS_MISSION1_LOOP 
	004F: create_thread @HOODS_RADAR 
	004E: end_thread
end
goto @HOODS_PHONE_START

:HOODS_RADAR
03A4: name_thread 'HOOD_BP'
wait 1000 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		03C6:   current_island == LEVEL_SUBURBAN
	then
		if
			0038:   $RUMBLE_COMPLETED == 1 
		then
			end_thread
		end
		if
			0038:   $DIABLOS_RADAR_DISPLAYED == 0
		then
			0164: disable_marker $HOODS_PHONE_MARKER 
			02A7: $HOODS_PHONE_MARKER = create_icon_marker_and_sphere RADAR_SPRITE_HOOD at -443.5 -6.0625 3.75
			0004: $DIABLOS_RADAR_DISPLAYED = 1
		end
	else
		if
			0038:   $DIABLOS_RADAR_DISPLAYED == 1
		then
			0164: disable_marker $HOODS_PHONE_MARKER 
			0004: $DIABLOS_RADAR_DISPLAYED = 0
		end
	end
end
goto @HOODS_RADAR

:HOODS_MISSION1_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $UZI_MONEY_COMPLETED == 1
then
	024E: disable_phone $PHONE_HOODS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_HOODS
else
	024E: disable_phone $PHONE_HOODS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_HOODS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'HM_1' duration 15000 ms style 2  // 'UZI MONEY'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M74_UZIMONEY ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @HOODS_MISSION1_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @HOODS_MISSION1_LOOP
			end
		end //while
	end
end
goto @HOODS_MISSION1_LOOP

:HOODS_MISSION2_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $TOYMINATOR_COMPLETED == 1
then
	024E: disable_phone $PHONE_HOODS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_HOODS
else
	024E: disable_phone $PHONE_HOODS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_HOODS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'HM_2' duration 15000 ms style 2  // 'TOYMINATOR'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M75_TOYMINATOR ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @HOODS_MISSION2_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @HOODS_MISSION2_LOOP
			end
		end //while
	end
end
goto @HOODS_MISSION2_LOOP

:HOODS_MISSION3_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $RIGGED_TO_BLOW_COMPLETED == 1
then
	024E: disable_phone $PHONE_HOODS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_HOODS
else
	024E: disable_phone $PHONE_HOODS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_HOODS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'HM_3' duration 15000 ms style 2  // 'RIGGED TO BLOW'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M76_RIGGEDTOBLOW ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @HOODS_MISSION3_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @HOODS_MISSION3_LOOP
			end
		end //while
	end
end
goto @HOODS_MISSION3_LOOP

:HOODS_MISSION4_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $BULLION_RUN_COMPLETED == 1
then
	024E: disable_phone $PHONE_HOODS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_HOODS
else
	024E: disable_phone $PHONE_HOODS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_HOODS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'HM_4' duration 15000 ms style 2  // 'BULLION RUN'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M77_BULLIONRUN ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @HOODS_MISSION4_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @HOODS_MISSION4_LOOP
			end
		end //while
	end
end
goto @HOODS_MISSION4_LOOP

:HOODS_MISSION5_LOOP
0001: wait $WAIT_TIME ms
end_thread ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if
	0038:   $RUMBLE_COMPLETED == 1
then
	024E: disable_phone $PHONE_HOODS 
	end_thread
end
if
	0038:   $ONMISSION == 0
then
	0405: enable_phone $PHONE_HOODS
else
	024E: disable_phone $PHONE_HOODS
end
if and
	0256:   is_player $PLAYER_CHAR defined
	0038:   $ONMISSION == 0
then
	if
		00F9:   player $PLAYER_CHAR stopped 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
	then
		if
			03EE:   player $PLAYER_CHAR controllable 
		then
			024E: disable_phone $PHONE_HOODS 
			03EF: player $PLAYER_CHAR make_safe 
			0169: set_fade_color 0 0 0 
			016A: fade 0 for 1500 ms 
			00BA: print_big 'HM_5' duration 15000 ms style 2  // 'RUMBLE'
			while fading
				wait 0 ms
			end
			// 0417: start_mission M78_RUMBLE ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		end
		if
			8256:   not is_player $PLAYER_CHAR defined 
		then
			goto @HOODS_MISSION5_LOOP
		end
		while 00F6:   player $PLAYER_CHAR 0 -443.5 -6.0625 3.75 radius 1.0 1.0 2.0 
			wait 0 ms
			if
				8256:   not is_player $PLAYER_CHAR defined 
			then
				goto @HOODS_MISSION5_LOOP
			end
		end //while
	end
end
goto @HOODS_MISSION5_LOOP


//#####################################################################################
//#####################################################################################
// END HOODS MONITORS / BEGIN THE EXCHANGE MONITOR
//#####################################################################################
//#####################################################################################

:CAT_MISSION1_LOOP
0001: wait $WAIT_TIME ms
if
	0038:   $THE_EXCHANGE_COMPLETED == 1 
then
	end_thread
end
if and
	0256:   is_player $PLAYER_CHAR defined 
	0038:   $ONMISSION == 0 
then
	if
		00F6:   player $PLAYER_CHAR 0 -362.75 246.5 60.0 radius 4.5 4.5 2.0 
	then
		if or
			0038:   $NICKED_HALF_A_MIL_BEFORE == 1
			010A:   player $PLAYER_CHAR money > 499999
		then
			if
				03EE:   player $PLAYER_CHAR controllable 
			then
				03EF: player $PLAYER_CHAR make_safe 
				0169: set_fade_color 0 0 0 
				016A: fade 0 for 1500 ms 
				03AF: set_streaming 0 
				00BA: print_big 'CAT2' duration 15000 ms style 2  // 'THE EXCHANGE'
				while fading
					wait 0 ms
				end
				0417: start_mission M79_THEEXCHANGE
			end
			if
				8256:   not is_player $PLAYER_CHAR defined
			then
				goto @CAT_MISSION1_LOOP
			end
			while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
				wait 0 ms
				if
					8256:   not is_player $PLAYER_CHAR defined
				then
					goto @CAT_MISSION1_LOOP
				end
			end //while
		else
			if
				0038:   $NICKED_HALF_A_MIL_BEFORE == 0
			then
				00BC: print_now 'CAT_MON' duration 5000 ms flag 1  // ~g~You don't have enough money yet. You need $500,000.
			end
			while 00F6:   player $PLAYER_CHAR 0 1455.688 -187.25 55.5625 radius 1.0 1.0 2.0
				wait 0 ms
				if
					8256:   not is_player $PLAYER_CHAR defined
				then
					goto @CAT_MISSION1_LOOP
				end
			end //while
		end
	end
end
goto @CAT_MISSION1_LOOP

//#####################################################################################
//#####################################################################################
// END THE EXCHANGE MONITOR
//#####################################################################################
//#####################################################################################
