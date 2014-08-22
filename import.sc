:IE_GARAGE_IND
03A4: name_thread 'IMPORT1'
if
	0038:   $DEBUGIEGARAGESCOMPLETED == 1
then
	goto @IE_EXPORT_LOOP
end

:IE_IMPORT_START
wait 500 ms
if
	0256:   is_player $PLAYER_CHAR defined
then
	if
		0121:   player $PLAYER_CHAR in_zone 'PORT_E'  // Portland Harbor
	then
		if
			8038:   not $INDUSTRIAL_GARAGE_SLOTS_FILLED == 16
		then
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 1
				0038:   $INDUSTRIAL_SLOT1 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $SECURICAR_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 13.5625
				01C7: remove_object_from_mission_cleanup_list $SECURICAR_SCORE_OFF 
				0177: set_object $SECURICAR_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT1 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 2
				0038:   $INDUSTRIAL_SLOT2 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $MOONBEAM_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 13.375
				01C7: remove_object_from_mission_cleanup_list $MOONBEAM_SCORE_OFF 
				0177: set_object $MOONBEAM_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT2 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 3
				0038:   $INDUSTRIAL_SLOT3 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $COACH_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 13.125
				01C7: remove_object_from_mission_cleanup_list $COACH_SCORE_OFF 
				0177: set_object $COACH_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT3 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 4
				0038:   $INDUSTRIAL_SLOT4 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $FLATBED_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 12.9375
				01C7: remove_object_from_mission_cleanup_list $FLATBED_SCORE_OFF 
				0177: set_object $FLATBED_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT4 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 5
				0038:   $INDUSTRIAL_SLOT5 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $LINERUNNER_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 12.75
				01C7: remove_object_from_mission_cleanup_list $LINERUNNER_SCORE_OFF 
				0177: set_object $LINERUNNER_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT5 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 6
				0038:   $INDUSTRIAL_SLOT6 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $TRASHMASTER_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 12.5
				01C7: remove_object_from_mission_cleanup_list $TRASHMASTER_SCORE_OFF 
				0177: set_object $TRASHMASTER_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT6 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 7
				0038:   $INDUSTRIAL_SLOT7 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $PATRIOT_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 12.3125
				01C7: remove_object_from_mission_cleanup_list $PATRIOT_SCORE_OFF 
				0177: set_object $PATRIOT_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT7 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 8
				0038:   $INDUSTRIAL_SLOT8 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $WHOOPEE_SCORE_OFF = create_object #LINE at 1496.0 -674.5625 12.125
				01C7: remove_object_from_mission_cleanup_list $WHOOPEE_SCORE_OFF 
				0177: set_object $WHOOPEE_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT8 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 9
				0038:   $INDUSTRIAL_SLOT9 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $BLISTA_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 13.5625
				01C7: remove_object_from_mission_cleanup_list $BLISTA_SCORE_OFF 
				0177: set_object $BLISTA_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT9 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 10
				0038:   $INDUSTRIAL_SLOT10 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $MULE_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 13.3125
				01C7: remove_object_from_mission_cleanup_list $MULE_SCORE_OFF 
				0177: set_object $MULE_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT10 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 11
				0038:   $INDUSTRIAL_SLOT11 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $YANKEE_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 13.125
				01C7: remove_object_from_mission_cleanup_list $YANKEE_SCORE_OFF 
				0177: set_object $YANKEE_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT11 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 12
				0038:   $INDUSTRIAL_SLOT12 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $BOBCAT_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 12.9375
				01C7: remove_object_from_mission_cleanup_list $BOBCAT_SCORE_OFF 
				0177: set_object $BOBCAT_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT12 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 13
				0038:   $INDUSTRIAL_SLOT13 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $DODO_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 12.75
				01C7: remove_object_from_mission_cleanup_list $DODO_SCORE_OFF 
				0177: set_object $DODO_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT13 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 14
				0038:   $INDUSTRIAL_SLOT14 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $BUS_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 12.5
				01C7: remove_object_from_mission_cleanup_list $BUS_SCORE_OFF 
				0177: set_object $BUS_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT14 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 15
				0038:   $INDUSTRIAL_SLOT15 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $RUMPO_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 12.3125
				01C7: remove_object_from_mission_cleanup_list $RUMPO_SCORE_OFF 
				0177: set_object $RUMPO_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT15 = 1
			end
			if and
				03D4:   garage $PORTLAND_IE_GARAGE contains_neededcar 16
				0038:   $INDUSTRIAL_SLOT16 == 0
			then
				0008: $INDUSTRIAL_GARAGE_SLOTS_FILLED += 1
				029B: $PONY_SCORE_OFF = create_object #LINE at 1496.0 -675.4375 12.125
				01C7: remove_object_from_mission_cleanup_list $PONY_SCORE_OFF 
				0177: set_object $PONY_SCORE_OFF z_angle_to 270.0 
				030C: set_mission_points += 1 
				0004: $INDUSTRIAL_SLOT16 = 1
			end
		else // not $INDUSTRIAL_GARAGE_SLOTS_FILLED == 16
			if 
				0038:   $CHANGED_INDUSTRIAL_GARAGE_BEFORE == 0
			then
				02FA: garage $PORTLAND_IE_GARAGE change_to_type GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE
				0004: $CHANGED_INDUSTRIAL_GARAGE_BEFORE = 1
			end
			goto @IE_EXPORT_LOOP
		end
	end
end
goto @IE_IMPORT_START


:IE_EXPORT_LOOP
wait 500 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		0056:   is_player_in_area_2d $PLAYER_CHAR coords 1486.875 -686.1875 to 1524.063 -666.75 sphere 0 
	then
		if
			0038:   $CREATE_CAR_PICKUPS_INDUSTRIAL == 0
		then
			032B: $SECURICAR_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 1 at 1501.0 -683.0 12.0625 
			032B: $MOONBEAM_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 2 at 1505.0 -683.0 12.0625 
			032B: $COACH_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 3 at 1509.0 -683.0 12.0625 
			032B: $FLATBED_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 4 at 1513.0 -683.0 12.0625 
			032B: $LINERUNNER_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 5 at 1517.0 -683.0 12.0625 
			032B: $TRASHMASTER_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 6 at 1521.0 -683.0 12.0625 
			032B: $PATRIOT_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 7 at 1521.0 -680.0 12.0625 
			032B: $WHOOPEE_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 8 at 1521.0 -677.0 12.0625 
			032B: $BLISTA_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 9 at 1521.0 -674.0 12.0625 
			032B: $MULE_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 10 at 1521.0 -671.0 12.0625 
			032B: $YANKEE_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 11 at 1521.0 -668.0 12.0625 
			032B: $BOBCAT_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 12 at 1517.0 -668.0 12.0625 
			032B: $DODO_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 13 at 1513.0 -668.0 12.0625 
			032B: $BUS_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 14 at 1509.0 -668.0 12.0625 
			032B: $RUMPO_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 15 at 1505.0 -668.0 12.0625 
			032B: $PONY_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 16 at 1501.0 -668.0 12.0625 
			wait 1000 ms
			0360: open_garage $PORTLAND_IE_GARAGE 
			0004: $CREATE_CAR_PICKUPS_INDUSTRIAL = 1
		end
		if
			0038:   $IMPORT_CAR_BEEN_CREATED_BEFORE == 0
		then
			if
				0214:   pickup $SECURICAR_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_SECURICAR
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $MOONBEAM_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_MOONBEAM
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $COACH_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_COACH
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $FLATBED_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_FLATBED
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $LINERUNNER_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_LINERUNNER
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $TRASHMASTER_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_TRASH
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $PATRIOT_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_PATRIOT
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $WHOOPEE_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_MRWHOOPY
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $BLISTA_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_BLISTA
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $MULE_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_MULE
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $YANKEE_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_YANKEE
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $BOBCAT_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_BOBCAT
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $DODO_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = PLANE_DODO
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $BUS_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_BUS
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $RUMPO_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_RUMPO
				goto @IE_LOAD_VEHICLE
			end
			if
				0214:   pickup $PONY_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE = CAR_PONY
				goto @IE_LOAD_VEHICLE
			end
		end
	else // player_in_area
		if
			0038:   $CREATE_CAR_PICKUPS_INDUSTRIAL == 1
		then
			0361: close_garage $PORTLAND_IE_GARAGE 
			while 83B1:   not garage $PORTLAND_IE_GARAGE door_closed
				wait 0 ms
			end
			gosub @IE_EXPORT_DESTROY
			if and
				0038:   $IMPORT_CAR_BEEN_CREATED_BEFORE == 1
				8119:   not car $IMPORTED_CAR wrecked
			then
				if
					00B0:   is_car_in_area_2d $IMPORTED_CAR from 1496.75 -686.1875 to 1523.25 -666.75 sphere 0 
				then
					00A6: delete_car $IMPORTED_CAR
				else
					01C3: remove_references_to_car $IMPORTED_CAR
				end
				0004: $IMPORT_CAR_BEEN_CREATED_BEFORE = 0
				0004: $CREATE_CAR_PICKUPS_INDUSTRIAL = 0
			end
		end
	end
end	
goto @IE_EXPORT_LOOP

:IE_LOAD_VEHICLE
00BC: print_now 'IMPORT1' duration 5000 ms flag 2  // Go outside and wait for your vehicle.
gosub @IE_EXPORT_DESTROY
if
	0256:   is_player $PLAYER_CHAR defined 
then
	while 8056:   not is_player_in_area_2d $PLAYER_CHAR coords 1486.875 -686.1875 to 1495.5 -674.0625 sphere 0 
		wait 0 ms
	end
	if
		0256:   is_player $PLAYER_CHAR defined 
	then
		01B4: set_player $PLAYER_CHAR controllable 0 
	end
	0361: close_garage $PORTLAND_IE_GARAGE
	while 83B1:   not garage $PORTLAND_IE_GARAGE door_closed
		wait 0 ms
	end
	0247: request_model $IMPORT_CAR_TYPE
	while 8248:   not model $IMPORT_CAR_TYPE available
		wait 0 ms
	end
	00A5: $IMPORTED_CAR = create_car $IMPORT_CAR_TYPE at 1504.063 -680.0625 12.0625
	0249: release_model $IMPORT_CAR_TYPE
	0175: set_car $IMPORTED_CAR z_angle_to 90.0 
	020A: set_car $IMPORTED_CAR door_status_to CARLOCK_UNLOCKED
	wait 1000 ms
	0360: open_garage $PORTLAND_IE_GARAGE 
	if
		0256:   is_player $PLAYER_CHAR defined 
	then
		01B4: set_player $PLAYER_CHAR controllable 1
	end
	0004: $IMPORT_CAR_BEEN_CREATED_BEFORE = 1
end
goto @IE_EXPORT_LOOP


:IE_EXPORT_DESTROY
0215: destroy_pickup $SECURICAR_PICKUP 
0215: destroy_pickup $MOONBEAM_PICKUP 
0215: destroy_pickup $COACH_PICKUP 
0215: destroy_pickup $FLATBED_PICKUP 
0215: destroy_pickup $LINERUNNER_PICKUP 
0215: destroy_pickup $TRASHMASTER_PICKUP 
0215: destroy_pickup $PATRIOT_PICKUP 
0215: destroy_pickup $WHOOPEE_PICKUP 
0215: destroy_pickup $BLISTA_PICKUP 
0215: destroy_pickup $MULE_PICKUP 
0215: destroy_pickup $YANKEE_PICKUP 
0215: destroy_pickup $BOBCAT_PICKUP 
0215: destroy_pickup $DODO_PICKUP
0215: destroy_pickup $BUS_PICKUP 
0215: destroy_pickup $RUMPO_PICKUP 
0215: destroy_pickup $PONY_PICKUP 
return 

//#####################################################################################
//#####################################################################################
// END PORTLAND I/E GARAGE / BEGIN EV CRANE
//#####################################################################################
//#####################################################################################

:EMERGENCY_CRANE
03A4: name_thread 'M_CRANE'

:EVC_START
wait 500 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		0056:   is_player_in_area_2d $PLAYER_CHAR coords 1548.063 -745.5 to 1583.0 -675.0625 sphere 0 
	then
		if or
			03EC:   ev_crane_collected_all_cars
			0038:   $DEBUGIEGARAGESCOMPLETED == 1
		then
			if
				0038:   $CRAN_ACTIVATED_BEFORE == 0
			then
				01EF: deactivate_crane 1570.25 -675.375 
				01EE: activate_crane 1570.25 -675.375 1638.688 -687.0625 1647.875 -700.0625 1571.063 -696.5 16.0 0.0 
				0109: player $PLAYER_CHAR money += 200000 
				030C: set_mission_points += 7 
				0004: $CRAN_ACTIVATED_BEFORE = 1
			end
			if
				8339:   not objects_in_cube 1565.875 -706.6875 9.0 to 1577.188 -686.25 20.0 flags 0 1 0 0 0  
			then
				if and
					0038:   $CREATE_MILITARY_PICKUPS == 0
					0038:   $MILITARY_CAR_BEEN_CREATED_BEFORE == 0
				then
					032B: $COPCAR_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 39 at 1571.0 -687.0 11.75 
					032B: $SWATVAN_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 35 at 1571.0 -691.0 11.75 
					032B: $FBI_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 36 at 1571.0 -694.0 11.75 
					032B: $TANK_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 37 at 1571.0 -697.0 11.75 
					032B: $BARRACKS_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 38 at 1571.0 -700.0 11.75 
					032B: $AMBULANCE_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 34 at 1571.0 -703.0 11.75 
					032B: $FIRETRUCK_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 33 at 1571.0 -706.0 11.75 
					$CREATE_MILITARY_PICKUPS = 1
				end
			end
			if
				0038:   $MILITARY_CAR_BEEN_CREATED_BEFORE == 0	
			then
				if
					0214:   pickup $COPCAR_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_POLICE
					goto @EVC_LOAD_VEHICLE
				end
				if
					0214:   pickup $SWATVAN_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_ENFORCER
					goto @EVC_LOAD_VEHICLE
				end
				if
					0214:   pickup $FBI_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_FBI
					goto @EVC_LOAD_VEHICLE
				end
				if
					0214:   pickup $BARRACKS_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_BARRACKS
					goto @EVC_LOAD_VEHICLE
				end
				if
					0214:   pickup $TANK_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_RHINO
					goto @EVC_LOAD_VEHICLE
				end
				if
					0214:   pickup $AMBULANCE_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_AMBULANCE
					goto @EVC_LOAD_VEHICLE
				end
				if
					0214:   pickup $FIRETRUCK_PICKUP picked_up
				then
					0004: $MILITARY_CAR_TYPE3 = CAR_FIRETRUCK
					goto @EVC_LOAD_VEHICLE
				end
			end
		end
	else // player_in_area
		if
			0038:   $CREATE_MILITARY_PICKUPS == 1
		then
			gosub @EVC_DESTROY
			0004: $CREATE_MILITARY_PICKUPS = 0
		end
		if and
			0256:   is_player $PLAYER_CHAR defined 
			0038:   $MILITARY_CAR_BEEN_CREATED_BEFORE == 1
		then
			if
				0119:   car $MILITARY_CAR wrecked 
			then
				0004: $MILITARY_CAR_BEEN_CREATED_BEFORE = 0
			else
				if or
					80B0:   not is_car_in_area_2d $MILITARY_CAR from 1668.563 -685.6875 to 1548.063 -745.5 sphere 0
					8121:   not player $PLAYER_CHAR in_zone 'PORT_E'  // Portland Harbor
				then
					01C3: remove_references_to_car $MILITARY_CAR
					0004: $MILITARY_CAR_BEEN_CREATED_BEFORE = 0
				end
			end
		end
	end
end
goto @EVC_START

:EVC_LOAD_VEHICLE
gosub @EVC_DESTROY
0247: request_model $MILITARY_CAR_TYPE3
while 8248:   not model $MILITARY_CAR_TYPE3 available
	wait 0 ms
end
00A5: $MILITARY_CAR = create_car $MILITARY_CAR_TYPE3 at 1643.188 -693.1875 -100.0
0249: release_model $MILITARY_CAR_TYPE3
0175: set_car $MILITARY_CAR z_angle_to 0.0 
020A: set_car $MILITARY_CAR door_status_to CARLOCK_UNLOCKED
0004: $MILITARY_CAR_BEEN_CREATED_BEFORE = 1
goto @EVC_START				

:EVC_DESTROY
0215: destroy_pickup $COPCAR_PICKUP 
0215: destroy_pickup $SWATVAN_PICKUP 
0215: destroy_pickup $FBI_PICKUP 
0215: destroy_pickup $TANK_PICKUP 
0215: destroy_pickup $BARRACKS_PICKUP 
0215: destroy_pickup $AMBULANCE_PICKUP 
0215: destroy_pickup $FIRETRUCK_PICKUP 
return 

//#####################################################################################
//#####################################################################################
// END EV CRANE / BEGIN SSV I/E GARAGE
//#####################################################################################
//#####################################################################################

:IE_GARAGE_SUB
03A4: name_thread 'IMPORT2'
if
	0038:   $DEBUGIEGARAGESCOMPLETED == 1
then
	goto @IE_EXPORT_LOOP_SSV
end

:IE_IMPORT_START_SSV
wait 500 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		0121:   player $PLAYER_CHAR in_zone 'BIG_DAM'  // Cochrane Dam
	then
		if
			8038:   not $SUBURBAN_GARAGE_SLOTS_FILLED == 16
		then
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 1
				0038:   $SUBURBAN_SLOT1 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $SENTINET_SCORE_OFF = create_object #LINE at -1106.125 151.1875 60.5
				01C7: remove_object_from_mission_cleanup_list $SENTINET_SCORE_OFF 
				0177: set_object $SENTINET_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT1 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 2
				0038:   $SUBURBAN_SLOT2 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $CHEETAH_SCORE_OFF = create_object #LINE at -1106.125 151.1875 60.3125
				01C7: remove_object_from_mission_cleanup_list $CHEETAH_SCORE_OFF 
				0177: set_object $CHEETAH_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT2 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 3
				0038:   $SUBURBAN_SLOT3 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $BANSHEE_SCORE_OFF = create_object #LINE at -1106.125 151.1875 60.125
				01C7: remove_object_from_mission_cleanup_list $BANSHEE_SCORE_OFF 
				0177: set_object $BANSHEE_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT3 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 4
				0038:   $SUBURBAN_SLOT4 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $IDAHO_SCORE_OFF = create_object #LINE at -1106.125 151.1875 59.875
				01C7: remove_object_from_mission_cleanup_list $IDAHO_SCORE_OFF 
				0177: set_object $IDAHO_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT4 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 5
				0038:   $SUBURBAN_SLOT5 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $INFERNUS_SCORE_OFF = create_object #LINE at -1106.125 151.1875 59.6875
				01C7: remove_object_from_mission_cleanup_list $INFERNUS_SCORE_OFF 
				0177: set_object $INFERNUS_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT5 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 6
				0038:   $SUBURBAN_SLOT6 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $TAXI_SCORE_OFF = create_object #LINE at -1106.125 151.1875 59.4375
				01C7: remove_object_from_mission_cleanup_list $TAXI_SCORE_OFF 
				0177: set_object $TAXI_SCORE_OFF z_angle_to 180.0
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT6 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 7
				0038:   $SUBURBAN_SLOT7 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $KURUMA_SCORE_OFF = create_object #LINE at -1106.125 151.1875 59.25
				01C7: remove_object_from_mission_cleanup_list $KURUMA_SCORE_OFF 
				0177: set_object $KURUMA_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT7 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 8
				0038:   $SUBURBAN_SLOT8 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $STRETCH_SCORE_OFF = create_object #LINE at -1106.125 151.1875 59.0625
				01C7: remove_object_from_mission_cleanup_list $STRETCH_SCORE_OFF 
				0177: set_object $STRETCH_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT8 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 9
				0038:   $SUBURBAN_SLOT9 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $PERENNIAL_SCORE_OFF = create_object #LINE at -1107.0 151.1875 60.5
				01C7: remove_object_from_mission_cleanup_list $PERENNIAL_SCORE_OFF 
				0177: set_object $PERENNIAL_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT9 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 10
				0038:   $SUBURBAN_SLOT10 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $STINGER_SCORE_OFF = create_object #LINE at -1107.0 151.1875 60.25
				01C7: remove_object_from_mission_cleanup_list $STINGER_SCORE_OFF 
				0177: set_object $STINGER_SCORE_OFF z_angle_to 180.0
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT10 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 11
				0038:   $SUBURBAN_SLOT11 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $MANANA_SCORE_OFF = create_object #LINE at -1107.0 151.1875 60.0625
				01C7: remove_object_from_mission_cleanup_list $MANANA_SCORE_OFF 
				0177: set_object $MANANA_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT11 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 12
				0038:   $SUBURBAN_SLOT12 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $LANDSTALKER_SCORE_OFF = create_object #LINE at -1107.0 151.1875 59.875
				01C7: remove_object_from_mission_cleanup_list $LANDSTALKER_SCORE_OFF 
				0177: set_object $LANDSTALKER_SCORE_OFF z_angle_to 180.0
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT12 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 13
				0038:   $SUBURBAN_SLOT13 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $STALLION_SCORE_OFF = create_object #LINE at -1107.0 151.1875 59.6875
				01C7: remove_object_from_mission_cleanup_list $STALLION_SCORE_OFF 
				0177: set_object $STALLION_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT13 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 14
				0038:   $SUBURBAN_SLOT14 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $INJECTION_SCORE_OFF = create_object #LINE at -1107.0 151.1875 59.4375
				01C7: remove_object_from_mission_cleanup_list $INJECTION_SCORE_OFF 
				0177: set_object $INJECTION_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT14 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 15
				0038:   $SUBURBAN_SLOT15 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $CABBIE_SCORE_OFF = create_object #LINE at -1107.0 151.1875 59.25
				01C7: remove_object_from_mission_cleanup_list $CABBIE_SCORE_OFF 
				0177: set_object $CABBIE_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT15 = 1
			end
			if and
				03D4:   garage $SSV_IE_GARAGE contains_neededcar 16
				0038:   $SUBURBAN_SLOT16 == 0
			then
				0008: $SUBURBAN_GARAGE_SLOTS_FILLED += 1
				029B: $ESPERANTO_SCORE_OFF = create_object #LINE at -1107.0 151.1875 59.0625
				01C7: remove_object_from_mission_cleanup_list $ESPERANTO_SCORE_OFF 
				0177: set_object $ESPERANTO_SCORE_OFF z_angle_to 180.0 
				030C: set_mission_points += 1 
				0004: $SUBURBAN_SLOT16 = 1
			end
		else // not $SUBURBAN_GARAGE_SLOTS_FILLED == 16
			if 
				0038:   $CHANGED_SUBURBAN_GARAGE_BEFORE == 0
			then
				02FA: garage $SSV_IE_GARAGE change_to_type GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE
				0004: $CHANGED_SUBURBAN_GARAGE_BEFORE = 1
			end
			goto @IE_EXPORT_LOOP_SSV
		end
	end
end
goto @IE_IMPORT_START_SSV


:IE_EXPORT_LOOP_SSV
wait 500 ms
if
	0256:   is_player $PLAYER_CHAR defined 
then
	if
		0056:   is_player_in_area_2d $PLAYER_CHAR coords -1117.375 158.0625 to -1098.0 121.5 sphere 0 
	then
		if
			0038:   $CREATE_CAR_PICKUPS_SUBURBAN == 0
		then
			032B: $SENTINET_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 17 at -1115.0 145.5 59.0 
			032B: $CHEETAH_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 18 at -1115.0 142.0 59.0 
			032B: $BANSHEE_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 19 at -1115.0 138.5 59.0 
			032B: $IDAHO_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 20 at -1115.0 135.0 59.0 
			032B: $INFERNUS_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 21 at -1115.0 131.5 59.0 
			032B: $TAXI_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 22 at -1115.0 128.0 59.0 
			032B: $KURUMA_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 23 at -1112.0 128.0 59.0 
			032B: $STRETCH_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 24 at -1109.0 128.0 59.0 
			032B: $PERENNIAL_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 25 at -1106.0 128.0 59.0 
			032B: $STINGER_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 26 at -1103.0 128.0 59.0 
			032B: $MANANA_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 27 at -1100.0 128.0 59.0 
			032B: $LANDSTALKER_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 28 at -1100.0 131.5 59.0 
			032B: $STALLION_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 29 at -1100.0 135.0 59.0 
			032B: $INJECTION_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 30 at -1100.0 138.5 59.0 
			032B: $CABBIE_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 31 at -1100.0 142.0 59.0 
			032B: $ESPERANTO_PICKUP = create_weapon_pickup #BONUS type PICKUP_ONCE ammo 32 at -1100.0 145.5 59.0 
			wait 1000 ms
			0360: open_garage $SSV_IE_GARAGE 
			0004: $CREATE_CAR_PICKUPS_SUBURBAN = 1
		end
		if
			0038:   $IMPORT_CAR_BEEN_CREATED_BEFORE2 == 0
		then
			if
				0214:   pickup $SENTINET_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_SENTINEL
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $CHEETAH_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_CHEETAH
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $BANSHEE_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_BANSHEE
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $IDAHO_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_IDAHO
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $INFERNUS_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_INFERNUS
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $TAXI_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_TAXI
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $KURUMA_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_KURUMA
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $STRETCH_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_STRETCH
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $PERENNIAL_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_PERENNIAL
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $STINGER_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_STINGER
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $MANANA_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_MANANA
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $LANDSTALKER_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_LANDSTALKER
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $STALLION_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_STALLION
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $INJECTION_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_BFINJECTION
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $CABBIE_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_CABBIE
				goto @IE_LOAD_VEHICLE_SSV
			end
			if
				0214:   pickup $ESPERANTO_PICKUP picked_up
			then
				0004: $IMPORT_CAR_TYPE2 = CAR_ESPERANTO
				goto @IE_LOAD_VEHICLE_SSV
			end
		end
	else // player_in_area
		if
			0038:   $CREATE_CAR_PICKUPS_SUBURBAN == 1
		then
			0361: close_garage $SSV_IE_GARAGE 
			while 83B1:   not garage $SSV_IE_GARAGE door_closed
				wait 0 ms
			end
			gosub @IE_EXPORT_DESTROY_SSV
			if and
				0038:   $IMPORT_CAR_BEEN_CREATED_BEFORE2 == 1
				8119:   not car $IMPORTED_CAR2 wrecked
			then
				if
					00B0:   car $IMPORTED_CAR2 0 -1117.375 149.75 -1098.0 121.5 
				then
					00A6: destroy_car $IMPORTED_CAR2
				else
					01C3: remove_references_to_car $IMPORTED_CAR2
				end
				0004: $IMPORT_CAR_BEEN_CREATED_BEFORE2 = 0
				0004: $CREATE_CAR_PICKUPS_SUBURBAN = 0
			end
		end
	end
end	
goto @IE_EXPORT_LOOP_SSV

:IE_LOAD_VEHICLE_SSV
00BC: print_now 'IMPORT1' duration 5000 ms flag 2  // Go outside and wait for your vehicle.
gosub @IE_EXPORT_DESTROY
if
	0256:   is_player $PLAYER_CHAR defined 
then
	while 8056:   not is_player_in_area_2d $PLAYER_CHAR coords -1117.375 158.0625 to -1105.0 150.875 sphere 0 
		wait 0 ms
	end
	if
		0256:   is_player $PLAYER_CHAR defined 
	then
		01B4: set_player $PLAYER_CHAR controllable 0 
	end
	0361: close_garage $SSV_IE_GARAGE
	while 83B1:   not garage $SSV_IE_GARAGE door_closed
		wait 0 ms
	end
	0247: request_model $IMPORT_CAR_TYPE2
	while 8248:   not model $IMPORT_CAR_TYPE2 available
		wait 0 ms
	end
	00A5: $IMPORTED_CAR2 = create_car $IMPORT_CAR_TYPE2 at -1112.0 143.1875 59.0
	0249: release_model $IMPORT_CAR_TYPE2 
	0175: set_car $IMPORTED_CAR2 z_angle_to 0.0 
	020A: set_car $IMPORTED_CAR2 door_status_to CARLOCK_UNLOCKED
	wait 1000 ms
	0360: open_garage $SSV_IE_GARAGE 
	if
		0256:   is_player $PLAYER_CHAR defined 
	then
		01B4: set_player $PLAYER_CHAR controllable 1
	end
	0004: $IMPORT_CAR_BEEN_CREATED_BEFORE2 = 1
end
goto @IE_EXPORT_LOOP_SSV


:IE_EXPORT_DESTROY_SSV
0215: destroy_pickup $SENTINET_PICKUP 
0215: destroy_pickup $CHEETAH_PICKUP 
0215: destroy_pickup $BANSHEE_PICKUP 
0215: destroy_pickup $IDAHO_PICKUP 
0215: destroy_pickup $INFERNUS_PICKUP 
0215: destroy_pickup $TAXI_PICKUP 
0215: destroy_pickup $KURUMA_PICKUP 
0215: destroy_pickup $STRETCH_PICKUP 
0215: destroy_pickup $PERENNIAL_PICKUP 
0215: destroy_pickup $STINGER_PICKUP 
0215: destroy_pickup $MANANA_PICKUP 
0215: destroy_pickup $LANDSTALKER_PICKUP 
0215: destroy_pickup $STALLION_PICKUP 
0215: destroy_pickup $INJECTION_PICKUP 
0215: destroy_pickup $CABBIE_PICKUP 
0215: destroy_pickup $ESPERANTO_PICKUP  
return 

//#####################################################################################
//#####################################################################################
// END SSV I/E GARAGE
//#####################################################################################
//#####################################################################################
