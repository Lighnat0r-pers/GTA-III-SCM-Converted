:CAMERA
0004: $RAYS_CAMERA_1 = 0  
0004: $RAYS_CAMERA_2 = 0  
0004: $RAYS_CAMERA_3 = 0  
0004: $RAYS_CUTSCENE_FLAG = 0  
03A4: name_thread 'CAMERA'
0111: set_deatharrest_state 0 (disabled)

:MISSION_START_CAMERA
wait 70 ms
if and
	0256:   player $PLAYER_CHAR defined
	03C6:   current_island == 2
	0038:   $RAYS_CUTSCENE_FLAG == 0
then
	if
		0121:   player $PLAYER_CHAR in_zone 'PARK'  // Belleville Park
	then
		if
			0057:   player $PLAYER_CHAR 0 36.5 -734.5625 21.625 47.4375 -726.9375 24.4375
		then
			if
				0056:   player $PLAYER_CHAR 0 36.5 -729.375 47.4375 -726.9375
			then
				if
					$RAYS_CAMERA_1 == 0
				then
					01B4: set_player $PLAYER_CHAR frozen_state 0 (frozen)
					0169: set_fade_color 1 1 1
					016A: fade 0  200 ms
					$RAYS_CAMERA_1 = 1
				end
				if and
					$RAYS_CAMERA_1 == 1
					816B:   NOT   fading
				then
					if
						$RAYS_CAMERA_2 == 0
					then
						0055: put_player $PLAYER_CHAR at 38.9375 -727.8125 21.5625 
						0171: set_player $PLAYER_CHAR z_angle_to 225.0 
					end
					015F: set_camera_position 36.0 -728.3125 24.25 0.0 0.0 0.0 
					0452: enable_player_control_camera
					0160: point_camera 36.9375 -728.3125 23.875 2 
					016A: fade 1 (out)  200 ms
					$RAYS_CAMERA_1 = 2
				end
				if and
					$RAYS_CAMERA_1 == 2
					816B:   NOT   fading
				then
					01B4: set_player $PLAYER_CHAR frozen_state 1 (unfrozen)
					$RAYS_CAMERA_1 = 3
					$RAYS_CAMERA_2 = 0
					$RAYS_CAMERA_3 = 0
				end
			end
			if
				0056:   player $PLAYER_CHAR 0 44.25 -734.5625 47.4375 -729.375
			then
				if
					$RAYS_CAMERA_2 == 0
				then
					01B4: set_player $PLAYER_CHAR frozen_state 0 (frozen)
					016A: fade 0  200 ms
					$RAYS_CAMERA_2 = 1
				end
				if and
					$RAYS_CAMERA_2 == 1
					816B:   NOT   fading
				then
					015F: set_camera_position 46.6875 -727.125 22.5 0.0 0.0 0.0 
					0452: enable_player_control_camera
					0160: point_camera 46.4375 -728.0625 22.5625 2 
					016A: fade 1 (out)  200 ms
					$RAYS_CAMERA_2 = 2
				end
				if and
					$RAYS_CAMERA_2 == 2
					816B:   NOT   fading
				then
					01B4: set_player $PLAYER_CHAR frozen_state 1 (unfrozen)
						$RAYS_CAMERA_1 = 0
					$RAYS_CAMERA_2 = 3
					$RAYS_CAMERA_3 = 0
				end
			end
			if
				0056:   player $PLAYER_CHAR 0 36.5 -734.5625 44.25 -729.375 
			then
				if
					$RAYS_CAMERA_3 == 0
				then
					01B4: set_player $PLAYER_CHAR frozen_state 0 (frozen)
					016A: fade 0  200 ms
					$RAYS_CAMERA_3 = 1
				end
				if and
					$RAYS_CAMERA_3 == 1
					816B:   NOT   fading
				then
					015F: set_camera_position 46.5625 -733.875 23.9375 0.0 0.0 0.0 
					0452: enable_player_control_camera
					0160: point_camera 45.625 -733.5625 23.6875 2 
					016A: fade 1 (out)  200 ms
					$RAYS_CAMERA_3 = 2
				end
				if and
					$RAYS_CAMERA_3 == 2
					816B:   NOT   fading
				then
					01B4: set_player $PLAYER_CHAR frozen_state 1 (unfrozen)
					$RAYS_CAMERA_1 = 0
					$RAYS_CAMERA_2 = 0
					$RAYS_CAMERA_3 = 3
				end
			end
		else
			if
				$RAYS_CAMERA_1 == 3
			then
				01B4: set_player $PLAYER_CHAR frozen_state 0 (frozen)
				016A: fade 0  200 ms
				$RAYS_CAMERA_1 = 4
			end
			if and
				$RAYS_CAMERA_1 == 4
				816B:   NOT   fading
			then
				0395: clear_area 1 at 38.875 -726.0 range 22.1875 2.0 
				0055: put_player $PLAYER_CHAR at 38.875 -726.0 21.5625 
				0171: set_player $PLAYER_CHAR z_angle_to 0.0 
				02EB: restore_camera_with_jumpcut 
				03C8: rotate_player-180-degrees 
				016A: fade 1 (out)  200 ms
				$RAYS_CAMERA_1 = 5
			end
			if and
				$RAYS_CAMERA_1 == 5
				816B:   NOT   fading
			then
				01B4: set_player $PLAYER_CHAR frozen_state 1 (unfrozen)
				$RAYS_CAMERA_1 = 0
				$RAYS_CAMERA_2 = 0
				$RAYS_CAMERA_3 = 0
			end
		end
	else // player_in_zone 'PARK'
		if or
			not $RAYS_CAMERA_1 == 0
			not $RAYS_CAMERA_2 == 0
			not $RAYS_CAMERA_3 == 0
		then
			02EB: restore_camera_with_jumpcut 
			03C8: rotate_player-180-degrees
			$RAYS_CAMERA_1 = 0
			$RAYS_CAMERA_2 = 0
			$RAYS_CAMERA_3 = 0
		end
	end
end
jump @MISSION_START_CAMERA
