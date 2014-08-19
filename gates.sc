:GATES
0004: $GATE_SFX1 = 0  
0004: $GATE_SFX2 = 0  
0004: $GATE2_SFX1 = 0  
0004: $GATE2_SFX2 = 0  
0004: $GATE_SFX1_FISH = 0  
0004: $GATE_SFX2_FISH = 0  
0004: $GATE_SFX1_MEAT = 0  
0004: $GATE_SFX2_MEAT = 0  
0004: $NOT_CLUNKED_YET = 0  
03A4: name_thread 'GATES'
0111: set_deatharrest_state 0 (disabled)
004F: create_thread @FISH_FACTORY_GATE
004F: create_thread @DOG_FOOD_FACTORY_GATE
004F: create_thread @POLICE_GATE1
004F: create_thread @POLICE_GATE2
004F: create_thread @COLOMBIAN_GATE
004F: create_thread @PHILS_GATE
004F: create_thread @COLOMBIAN_GATE2
end_thread

// PORTLAND GATES
// ************************************************************************************

:FISH_FACTORY_GATE
03A4: name_thread 'FSH_GTE'

:FISH_FACTORY_GATE_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'PORT_W'  // Callahan Point
	then
		if
			0057:   player $PLAYER_CHAR 0 1025.188 -1108.375 12.0 1008.188 -1098.375 16.0
		then
			if or
				00DE:   player $PLAYER_CHAR driving_vehicle_type #BELLYUP 
				00DE:   player $PLAYER_CHAR driving_vehicle_type #TRASH 
				0057:   player $PLAYER_CHAR 0 1015.563 -1100.5 12.0 1009.188 -1108.063 16.0 
			then
				if
					0038:   $GATE_SFX1_FISH == 0
				then
					018C: play_sound 92 at 1016.0 -1107.938 12.25 
					0004: $GATE_SFX1_FISH = 1
				end
				while 834E:   not unknown_move_object $FISH_FACTORY_GATE to 1016.0 -1099.438 12.25 unknown_angle 0.0 0.0625 0.0 0
					wait 0 ms
				end
				if
					0038:   $NOT_CLUNKED_YET == 1
				then
					0004: $GATE_SFX2_FISH = 0
					0004: $NOT_CLUNKED_YET = 0
				end
				if
					0038:   $GATE_SFX2_FISH == 0
				then
					018C: play_sound 93 at 1016.0 -1099.438 12.25 
					0004: $GATE_SFX2_FISH = 1
				end
			end
		else	// player_within_coordinates
			if
				$GATE_SFX1_FISH == 1
			then
				018C: play_sound 92 at 1016.0 -1099.438 12.25
			end
			while 834E:   not unknown_move_object $FISH_FACTORY_GATE to 1016.0 -1107.938 12.25 unknown_angle 0.0 0.0625 0.0 1 
				wait 0 ms
			end
			01BB: store_object $FISH_FACTORY_GATE position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				$GATE_SFX2_FISH == 1
			then
				$NOT_CLUNKED_YET = 1
				if and
					$OBJECTX == 1016.0
					$OBJECTY == -1107.938
					$OBJECTZ == 12.25
				then
					018C: play_sound 93 at 1016.0 -1107.938 12.25
					$GATE_SFX2_FISH = 0
					$NOT_CLUNKED_YET = 0
				end
			end
			$GATE_SFX1_FISH = 0
		end
	end
end
jump @FISH_FACTORY_GATE_INNER

:DOG_FOOD_FACTORY_GATE
03A4: name_thread 'DOG_GTE'

:DOG_FOOD_FACTORY_GATE_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'PORT_I'  // Trenton
	then
		if
			02B3:   player $PLAYER_CHAR in_cube 1247.563 -822.0 12.0 1253.938 -815.0 18.0 radius 14.0 sphere 0 
		then
			if
				0038:   $GATE_SFX1_MEAT == 0
			then
				018C: play_sound 92 at 1250.375 -812.0 13.9375  
				0004: $GATE_SFX1_MEAT = 1
			end
			while 834E:   not unknown_move_object $DOGFOOD_FACTORY_GATE to 1244.375 -818.0 13.9375 unknown_angle 0.0625 0.0625 0.0 0
				wait 0 ms
			end
			if
				0038:   $NOT_CLUNKED_YET == 1
			then
				0004: $GATE_SFX2_MEAT = 0
				0004: $NOT_CLUNKED_YET = 0
			end
			if
				0038:   $GATE_SFX2_MEAT == 0
			then
				018C: play_sound 93 at 1244.375 -818.0 13.9375
				0004: $GATE_SFX2_MEAT = 1
			end
		else	// player_within_coordinates
			if
				$GATE_SFX1_MEAT == 1
			then
				018C: play_sound 92 at 1244.375 -818.0 13.9375
			end
			while 834E:   not unknown_move_object $DOGFOOD_FACTORY_GATE to 1250.375 -812.0 13.9375 unknown_angle 0.0625 0.0625 0.0 1
				wait 0 ms
			end
			01BB: store_object $DOGFOOD_FACTORY_GATE position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				$GATE_SFX2_MEAT == 1
			then
				$NOT_CLUNKED_YET = 1
				if and
					$OBJECTX == 1250.375
					$OBJECTY == -812.0
					$OBJECTZ == 13.9375
				then
					018C: play_sound 93 at 1250.375 -812.0 13.9375
					$GATE_SFX2_MEAT = 0
					$NOT_CLUNKED_YET = 0
				end
			end
			$GATE_SFX1_MEAT = 0
		end
	end
end
jump @DOG_FOOD_FACTORY_GATE_INNER

// END OF PORTLAND GATES ************************************************************
// STAUNTON GATES *******************************************************************

:POLICE_GATE1
03A4: name_thread 'POL_GT1'

:POLICE_GATE1_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'YAKUSA'  // Torrington
	then
		if
			0057:   player $PLAYER_CHAR 0 358.0 -1138.0 18.0 366.0 -1121.0 30.0 
		then
			if or
				00DE:   player $PLAYER_CHAR driving_vehicle_type #POLICE 
				0057:   player $PLAYER_CHAR 0 365.875 -1128.188 21.875 358.5 -1119.5 26.0 
			then
				if
					0038:   $GATE_SFX1 == 0
				then
					018C: play_sound 92 at 366.125 -1128.5 21.9375 
					0004: $GATE_SFX1 = 1
				end
				while 834E:   not unknown_move_object $POLICE_DOOR_ONE to 358.125 -1128.5 21.9375 unknown_angle 0.0625 0.0 0.0 0
					wait 0 ms
				end
				if
					0038:   $NOT_CLUNKED_YET == 1
				then
					0004: $GATE_SFX2 = 0
					0004: $NOT_CLUNKED_YET = 0
				end
				if
					0038:   $GATE_SFX2 == 0
				then
					018C: play_sound 93 at 358.125 -1128.5 21.9375 
					0004: $GATE_SFX2 = 1
				end
			end
		else	// player_within_coordinates
			if
				$GATE_SFX1 == 1
			then
				018C: play_sound 92 at 358.125 -1128.5 21.9375
			end
			while 834E:   not unknown_move_object $POLICE_DOOR_ONE to 366.125 -1128.5 21.9375 unknown_angle 0.0625 0.0 0.0 1 
				wait 0 ms
			end
			01BB: store_object $POLICE_DOOR_ONE position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				$GATE_SFX2 == 1
			then
				$NOT_CLUNKED_YET = 1
				if and
					$OBJECTX == 366.125
					$OBJECTY == -1128.5
					$OBJECTZ == 21.9375
				then
					018C: play_sound 93 at 366.125 -1128.5 21.9375
					$GATE_SFX2 = 0
					$NOT_CLUNKED_YET = 0
				end
			end
			$GATE_SFX1 = 0
		end
	end
end
jump @POLICE_GATE1_INNER

:POLICE_GATE2
03A4: name_thread 'POL_GT2'

:POLICE_GATE2_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'YAKUSA'  // Torrington
	then
		if
			0057:   player $PLAYER_CHAR 0 317.8125 -1136.0 18.0 322.8125 -1119.813 30.0 
		then
			if or
				00DE:   player $PLAYER_CHAR driving_vehicle_type #POLICE 
				0057:   player $PLAYER_CHAR 0 322.6875 -1128.063 21.875 318.0 -1119.25 26.0
			then
				if
					0038:   $GATE2_SFX1 == 0
				then
					018C: play_sound 92 at 326.25 -1128.5 21.9375 
					0004: $GATE2_SFX1 = 1
				end
				while 834E:   not unknown_move_object $POLICE_DOOR_TWO to 332.0 -1128.5 21.9375 unknown_angle 0.0625 0.0 0.0 0
					wait 0 ms
				end
				if
					0038:   $NOT_CLUNKED_YET == 1
				then
					0004: $GATE2_SFX2 = 0
					0004: $NOT_CLUNKED_YET = 0
				end
				if
					0038:   $GATE2_SFX2 == 0
				then
					018C: play_sound 93 at 332.0 -1128.5 21.9375
					0004: $GATE2_SFX2 = 1
				end
			end
		else	// player_within_coordinates
			if
				$GATE2_SFX1 == 1
			then
				018C: play_sound 92 at 332.0 -1128.5 21.9375
			end
			while 834E:   not unknown_move_object $POLICE_DOOR_TWO to 326.25 -1128.5 21.9375 unknown_angle 0.0625 0.0 0.0 1
				wait 0 ms
			end
			01BB: store_object $POLICE_DOOR_TWO position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				$GATE2_SFX2 == 1
			then
				$NOT_CLUNKED_YET = 1
				if and
					$OBJECTX == 326.25
					$OBJECTY == -1128.5
					$OBJECTZ == 21.9375
				then
					018C: play_sound 93 at 326.25 -1128.5 21.9375
					$GATE2_SFX2 = 0
					$NOT_CLUNKED_YET = 0
				end
			end
			$GATE2_SFX1 = 0
		end
	end
end
jump @POLICE_GATE2_INNER

:COLOMBIAN_GATE
03A4: name_thread 'COL_GTE'

:COLOMBIAN_GATE_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'STADIUM'  // Aspatria
	then
		if
			0057:   player $PLAYER_CHAR 0 98.5625 -337.5 14.0 81.875 -317.5 20.0
		then
			if or
				00DE:   player $PLAYER_CHAR driving_vehicle_type #COLUMB 
				0057:   player $PLAYER_CHAR 0 31.0 -317.0 14.0 91.0 -394.0 25.0
			then
				if
					0038:   $GATE2_SFX1 == 0
				then
					018C: play_sound 92 at 91.5625 -318.5625 15.25
					0004: $GATE2_SFX1 = 1
				end
				while 834E:   not unknown_move_object $COLOMBIAN_GATE to 91.5625 -327.3125 15.25 unknown_angle 0.0 0.0625 0.0 0
					wait 0 ms
				end
				if
					0038:   $NOT_CLUNKED_YET == 1
				then
					0004: $GATE2_SFX2 = 0
					0004: $NOT_CLUNKED_YET = 0
				end
				if
					0038:   $GATE2_SFX2 == 0
				then
					018C: play_sound 93 at 91.5625 -327.3125 15.25
					0004: $GATE2_SFX2 = 1
				end
			end
		else	// player_within_coordinates
			if
				$GATE2_SFX1 == 1
			then
				018C: play_sound 92 at 91.5625 -327.3125 15.25
			end
			while 834E:   not unknown_move_object  $COLOMBIAN_GATE to 91.5625 -318.5625 15.25 unknown_angle 0.0 0.0625 0.0 1
				wait 0 ms
			end
			01BB: store_object $COLOMBIAN_GATE position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				$GATE2_SFX2 == 1
			then
				$NOT_CLUNKED_YET = 1
				if and
					$OBJECTX == 91.5625
					$OBJECTY == -318.5625
					$OBJECTZ == 15.25
				then
					018C: play_sound 93 at 91.5625 -318.5625 15.25
					$GATE2_SFX2 = 0
					$NOT_CLUNKED_YET = 0
				end
			end
			$GATE2_SFX1 = 0
		end
	end
end
jump @COLOMBIAN_GATE_INNER

:PHILS_GATE
03A4: name_thread 'PHL_GTE'

:PHILS_GATE_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'HOSPI_2'  // Rockford
	then
		0004: $OPEN_PHILS_GATE = 0
		if or
			0038: $ARMS_SHORTAGE_COMPLETED == 1
			0038: $DEBUGUNLOCKISLANDS == 1
		then
			if
				0056:   player $PLAYER_CHAR 0 134.0 198.0 156.0 207.0
			then
				0004: $OPEN_PHILS_GATE = 1
			end
		else
			if
				0038: $ON_ARMS_SHORTAGE_MISSION == 1   //   If player is doing Phil mission
			then					       //   then this script doesn't move the gate at all.
				jump @PHILS_GATE_INNER		       //   The Phil script should do all the gate moving.
			end
			if
				0056:   player $PLAYER_CHAR 0 138.0 198.0 146.0 207.0
			then
				0004: $OPEN_PHILS_GATE = 1
			end
		end
		if
			0038: $OPEN_PHILS_GATE == 1
		then
			if
				0038: $GATE2_SFX1 == 0
			then
				018C: play_sound 92 at 147.1875 207.3125 10.5625
				0004: $GATE2_SFX1 = 1
			end
			while 834E:   not unknown_move_object $PHILS_COMPND_GATE to 147.1875 214.5 10.5625 unknown_angle 0.0 0.0625 0.0 0
				wait 0 ms
			end
			if
				0038:   $NOT_CLUNKED_YET == 1
			then
				0004: $GATE_SFX2 = 0
				0004: $NOT_CLUNKED_YET = 0
			end
			if
				0038:   $GATE2_SFX2 == 0
			then
				018C: play_sound 93 at 147.1875 214.5 10.5625
				0004: $GATE2_SFX2 = 1
			end
		else
			if
				0038:   $GATE2_SFX1 == 1
			then
				018C: play_sound 92 at 147.1875 214.5 10.5625
			end
			while 834E:   not unknown_move_object $PHILS_COMPND_GATE to 147.1875 207.3125 10.5625 unknown_angle 0.0 0.0625 0.0 1
				wait 0 ms
			end
			01BB: store_object $PHILS_COMPND_GATE position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				0038:   $GATE2_SFX2 == 1
			then
				0004: $NOT_CLUNKED_YET = 1
				if and
					0042:   $OBJECTX == -187.812
					0042:   $OBJECTY == 207.312
					0042:   $OBJECTZ == 16.5625
				then
					018C: play_sound 93 at 147.1875 207.3125 10.5625
					0004: $GATE2_SFX2 = 0
					0004: $NOT_CLUNKED_YET = 0
				end
			end
			0004: $GATE2_SFX1 = 0
		end
	end
end
jump @PHILS_GATE_INNER

// END OF STAUNTON GATES ************************************************************
// SHORESIDE GATES ******************************************************************

:COLOMBIAN_GATE2
03A4: name_thread 'COL2GTE'

:COLOMBIAN_GATE2_INNER
wait 1000 ms
if
	0256: player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'SWANKS'  // Cedar Grove
	then
		if
			0057:   player $PLAYER_CHAR 0 -367.25 241.0 59.0 -358.75 259.5625 65.0 
		then
			if or
				00DE:   player $PLAYER_CHAR driving_vehicle_type #COLUMB 
				0057:   player $PLAYER_CHAR 0 -367.25 251.0 59.0 -358.75 259.5625 65.0 
			then
				if
					0038:   $GATE_SFX1 == 0
				then
					018C: play_sound 92 at -363.0 250.4375 61.3125
					0004: $GATE_SFX1 = 1
				end
				while 834E:   not unknown_move_object $COLOMBIAN_GATE2 to -370.0 250.4375 61.3125 unknown_angle 0.0625 0.0 0.0 0
					wait 0 ms
				end
				if
					0038:   $NOT_CLUNKED_YET == 1
				then
					0004: $GATE_SFX2 = 0
					0004: $NOT_CLUNKED_YET = 0
				end
				if
					0038:   $GATE_SFX2 == 0
				then
					018C: play_sound 93 at -370.0 250.4375 61.3125
					0004: $GATE_SFX2 = 1
				end
			end
		else	// player_within_coordinates
			if
				$GATE_SFX1 == 1
			then
				018C: play_sound 92 at -370.0 250.4375 61.3125
			end
			while 834E:   not unknown_move_object $COLOMBIAN_GATE2 to -363.0 250.4375 61.3125 unknown_angle 0.0625 0.0 0.0 1
				wait 0 ms
			end
			01BB: store_object $COLOMBIAN_GATE2 position_to $OBJECTX $OBJECTY $OBJECTZ
			if
				$GATE_SFX2 == 1
			then
				$NOT_CLUNKED_YET = 1
				if and
					$OBJECTX == -363.0
					$OBJECTY == 250.4375
					$OBJECTZ == 61.3125
				then
					018C: play_sound 93 at -363.0 250.4375 61.3125
					$GATE_SFX2 = 0
					$NOT_CLUNKED_YET = 0
				end
			end
			$GATE_SFX1 = 0
		end
	end
end
jump @COLOMBIAN_GATE2_INNER
