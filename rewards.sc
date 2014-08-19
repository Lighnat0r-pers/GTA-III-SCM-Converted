:ALL_REWARDS
0004: $EARNED_FREE_PISTOL = 0  
0004: $EARNED_FREE_UZI = 0  
0004: $EARNED_FREE_ARMOUR = 0  
0004: $EARNED_FREE_SHOTGUN = 0  
0004: $EARNED_FREE_GRENADES = 0  
0004: $EARNED_FREE_MOLOTOVS = 0  
0004: $EARNED_FREE_AK47 = 0  
0004: $EARNED_FREE_SNIPER = 0  
0004: $EARNED_FREE_M16 = 0  
0004: $EARNED_FREE_LAUNCHER = 0  
0004: $EARNED_FREE_FLAMETHROWER = 0  
0004: $IND_COPCAR_KILLS = 0  
0004: $COM_COPCAR_KILLS = 0  
0004: $SUB_COPCAR_KILLS = 0  
0004: $IND_FIRES_EXTING = 0  
0004: $COM_FIRES_EXTING = 0 
0004: $SUB_FIRES_EXTING = 0  
0004: $TOTAL_SAVED_PEDS = 0  
0004: $PLAY_PAGER_MESSAGE1 = 0  
0004: $PLAY_PAGER_MESSAGE2 = 0  
0004: $PLAY_PAGER_MESSAGE3 = 0  
0004: $AMBULANCE_PAGER_FLAG = 0  
0004: $BONUS_WEAPONS_CREATED = 0  
0004: $IMPORT_CAR_BEEN_CREATED_BEFORE = 0           
0004: $IMPORT_CAR_BEEN_CREATED_BEFORE2 = 0  
0004: $MILITARY_CAR_BEEN_CREATED_BEFORE = 0
0004: $INDUSTRIAL_GARAGE_SLOTS_FILLED = 0
0004: $SUBURBAN_GARAGE_SLOTS_FILLED = 0
// 0004: $MILITARY_SLOTS_FILLED = 0 // NOT USED
0004: $CREATE_CAR_PICKUPS_INDUSTRIAL = 0
0004: $CREATE_CAR_PICKUPS_SUBURBAN = 0
0004: $CREATE_MILITARY_PICKUPS = 0
0004: $CHANGED_SUBURBAN_GARAGE_BEFORE = 0
0004: $CHANGED_INDUSTRIAL_GARAGE_BEFORE = 0
0004: $CRAN_ACTIVATED_BEFORE = 0
03A4: name_thread 'IMPORT'
0111: set_deatharrest_state 0 (disabled)
wait 100 ms
create_thread @IE_GARAGE_IND 
create_thread @EMERGENCY_CRANE 
create_thread @IE_GARAGE_SUB 
create_thread @PACKAGE_SNIFFER     

if
    0038:   $DEBUGPACKAGESCOMPLETED == 1  
then
    0004: $EARNED_FREE_PISTOL = 1  
    0004: $EARNED_FREE_UZI = 1  
    0004: $EARNED_FREE_ARMOUR = 1  
    0004: $EARNED_FREE_SHOTGUN = 1  
    0004: $EARNED_FREE_GRENADES = 1  
    0004: $EARNED_FREE_MOLOTOVS = 1  
    0004: $EARNED_FREE_AK47 = 1  
    0004: $EARNED_FREE_SNIPER = 1  
    0004: $EARNED_FREE_M16 = 1  
    0004: $EARNED_FREE_LAUNCHER = 1  
end
if
    0038:   $DEBUGPARAMEDICCOMPLETED == 1  
then
    0004: $TOTAL_SAVED_PEDS = 78  
    0330: set_player $PLAYER infinite_run_to 1 (true)
end
if
    0038:   $DEBUGVIGILANTECOMPLETED == 1  
then
    0004: $IND_COPCAR_KILLS = 20  
    0004: $COM_COPCAR_KILLS = 20  
    0004: $SUB_COPCAR_KILLS = 20  
end
if
    0038:   $DEBUGFIREFIGHTERCOMPLETED == 1  
then
    0004: $EARNED_FREE_FLAMETHROWER = 1  
end

while true
	wait 500 ms
	if
		0256:   player $PLAYER_CHAR defined 
	then
		if
			03C6:   current_island == 1 
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'REDLIGH'  // Red Light District
			then
				if
					0057:   player $PLAYER_CHAR 0 828.875 -283.75 0.0 894.1875 -323.5 26.0 
				then
					if
						$BONUS_WEAPONS_CREATED == 0 // integer values 
					then
						0213: $HIDEOUT_BAT = create_pickup #BAT type 2 at 883.5 -302.0625 8.0 
						if
							$EARNED_FREE_PISTOL == 1
						then
							032B: $BONUS_GUN1 = create_weapon_pickup #COLT45 14 ammo 90 at 876.75 -317.0625 10.0
						end
						if
							$EARNED_FREE_UZI == 1
						then
							032B: $BONUS_GUN2 = create_weapon_pickup #UZI 14 ammo 250 at 874.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_GRENADES == 1
						then
							032B: $BONUS_GUN3 = create_weapon_pickup #GRENADE 14 ammo 10 at 872.75 -317.0625 10.0
						end
						if
							$EARNED_FREE_SHOTGUN == 1
						then
							032B: $BONUS_GUN4 = create_weapon_pickup #SHOTGUN 14 ammo 50 at 870.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_ARMOUR == 1
						then
							0213: $BONUS_GUN5 = create_pickup #BODYARMOUR type 14 at 868.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_MOLOTOVS == 1
						then
							032B: $BONUS_GUN6 = create_weapon_pickup #MOLOTOV 14 ammo 10 at 866.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_AK47 == 1
						then
							032B: $BONUS_GUN7 = create_weapon_pickup #AK47 14 ammo 300 at 864.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_SNIPER == 1
						then
							032B: $BONUS_GUN8 = create_weapon_pickup #SNIPER 14 ammo 50 at 862.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_M16 == 1
						then
							032B: $BONUS_GUN9 = create_weapon_pickup #M16 14 ammo 600 at 860.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_FLAMETHROWER == 1
						then
							032B: $BONUS_GUNFLAME = create_weapon_pickup #FLAME 14 ammo 2000 at 858.75 -317.0625 10.0 
						end
						if
							$EARNED_FREE_LAUNCHER == 1
						then
							032B: $BONUS_GUN10 = create_weapon_pickup #ROCKET 14 ammo 50 at 856.75 -317.0625 10.0 
						end
						if
							$IND_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE1 = create_pickup #BRIBE type 14 at 877.5625 -313.75 8.5 
						end
						if
							$IND_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE2 = create_pickup #BRIBE type 14 at 875.5625 -313.75 8.5 
						end
						if
							$COM_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE3 = create_pickup #BRIBE type 14 at 873.5625 -313.75 8.5 
						end
						if
							$COM_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE4 = create_pickup #BRIBE type 14 at 871.5625 -313.75 8.5 
						end
						if
							$SUB_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE5 = create_pickup #BRIBE type 14 at 869.5625 -313.75 8.5 
						end
						if
							$SUB_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE6 = create_pickup #BRIBE type 14 at 867.5625 -313.75 8.5 
						end
						if
							$TOTAL_SAVED_PEDS > 34
						then
							0213: $BONUS_HEALTH = create_pickup #HEALTH type 14 at 878.875 -299.0 8.1875 
						end
						if
							$TOTAL_SAVED_PEDS > 69
						then
							0213: $BONUS_ADRENALINE = create_pickup #ADRENALINE type 14 at 876.875 -299.0 8.1875 
						end
						$BONUS_WEAPONS_CREATED = 1
					end
				else	//	player is not in area (828.9 -283.8 0.0 894.2 -323.5 26.0)
					if
						$BONUS_WEAPONS_CREATED == 1
					then
						gosub @REMOVE_BONUS_PICKUPS
					end
				end
			else	//	player is not in REDLIGH
				if
					$BONUS_WEAPONS_CREATED == 1
				then
					gosub @REMOVE_BONUS_PICKUPS
				end
			end
		end

		if
			03C6:   current_island == 2 
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'PARK'  // Belleville Park
			then
				if
					0057:   player $PLAYER_CHAR 0 39.5 -443.75 53.0 167.5 -484.5625 15.0 
				then
					if
						$BONUS_WEAPONS_CREATED == 0 // integer values 
					then
						0213: $HIDEOUT_BAT = create_pickup #BAT type 2 at 99.0625 -478.6875 15.875 
						if
							$EARNED_FREE_PISTOL == 1
						then
							032B: $BONUS_GUN1 = create_weapon_pickup #COLT45 14 ammo 90 at 132.6875 -477.875 15.875
						end
						if
							$EARNED_FREE_UZI == 1
						then
							032B: $BONUS_GUN2 = create_weapon_pickup #UZI 14 ammo 250 at 132.6875 -479.875 15.875 
						end
						if
							$EARNED_FREE_GRENADES == 1
						then
							032B: $BONUS_GUN3 = create_weapon_pickup #GRENADE 14 ammo 10 at 129.0625 -477.875 15.875 
						end
						if
							$EARNED_FREE_SHOTGUN == 1
						then
							032B: $BONUS_GUN4 = create_weapon_pickup #SHOTGUN 14 ammo 50 at 129.0625 -479.875 15.875 
						end
						if
							$EARNED_FREE_ARMOUR == 1
						then
							0213: $BONUS_GUN5 = create_pickup #BODYARMOUR type 14 at 125.5625 -477.875 15.875 
						end
						if
							$EARNED_FREE_MOLOTOVS == 1
						then
							032B: $BONUS_GUN6 = create_weapon_pickup #MOLOTOV 14 ammo 10 at 125.5625 -479.875 15.875 
						end
						if
							$EARNED_FREE_AK47 == 1
						then
							032B: $BONUS_GUN7 = create_weapon_pickup #AK47 14 ammo 300 at 122.0625 -477.875 15.875 
						end
						if
							$EARNED_FREE_SNIPER == 1
						then
							032B: $BONUS_GUN8 = create_weapon_pickup #SNIPER 14 ammo 50 at 122.0625 -479.875 15.875 
						end
						if
							$EARNED_FREE_M16 == 1
						then
							032B: $BONUS_GUN9 = create_weapon_pickup #M16 14 ammo 600 at 118.6875 -477.875 15.875 
						end
						if
							$EARNED_FREE_FLAMETHROWER == 1
						then
							032B: $BONUS_GUNFLAME = create_weapon_pickup #FLAME 14 ammo 2000 at 118.6875 -479.875 15.875 
						end
						if
							$EARNED_FREE_LAUNCHER == 1
						then
							032B: $BONUS_GUN10 = create_weapon_pickup #ROCKET 14 ammo 50 at 125.5625 -475.875 15.875 
						end
						if
							$IND_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE1 = create_pickup #BRIBE type 14 at 114.5 -458.0 15.5 
						end
						if
							$IND_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE2 = create_pickup #BRIBE type 14 at 112.5 -458.0 15.5 
						end
						if
							$COM_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE3 = create_pickup #BRIBE type 14 at 110.5 -458.0 15.5 
						end
						if
							$COM_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE4 = create_pickup #BRIBE type 14 at 108.5 -458.0 15.5 
						end
						if
							$SUB_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE5 = create_pickup #BRIBE type 14 at 106.5 -458.0 15.5 
						end
						if
							$SUB_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE6 = create_pickup #BRIBE type 14 at 104.5 -458.0 15.5 
						end
						if
							$TOTAL_SAVED_PEDS > 34
						then
							0213: $BONUS_HEALTH = create_pickup #HEALTH type 14 at 94.5625 -472.5 15.5 
						end
						if
							$TOTAL_SAVED_PEDS > 69
						then
							0213: $BONUS_ADRENALINE = create_pickup #ADRENALINE type 14 at 92.5625 -472.5 15.5 
						end
						$BONUS_WEAPONS_CREATED = 1
					end
				else	//	player is not in area (39.5 -443.8 53.0 167.5 -484.6 15.0)
					if
						$BONUS_WEAPONS_CREATED == 1
					then
						gosub @REMOVE_BONUS_PICKUPS
					end
				end
			else	//	player is not in PARK
				if
					$BONUS_WEAPONS_CREATED == 1
				then
					gosub @REMOVE_BONUS_PICKUPS
				end
			end
		end

		if
			03C6:   current_island == 3
		then
			if
				0121:   player $PLAYER_CHAR in_zone 'PROJECT'  // Wichita Gardens
			then
				if
					0057:   player $PLAYER_CHAR 0 -595.75 -68.1875 10.0 -700.6875 3.0625 30.0
				then
					if
						$BONUS_WEAPONS_CREATED == 0 // integer values 
					then
						0213: $HIDEOUT_BAT = create_pickup #BAT type 2 at -661.0625 -3.75 18.75
						if
							$EARNED_FREE_PISTOL == 1
						then
							032B: $BONUS_GUN1 = create_weapon_pickup #COLT45 14 ammo 90 at -650.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_UZI == 1
						then
							032B: $BONUS_GUN2 = create_weapon_pickup #UZI 14 ammo 250 at -648.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_GRENADES == 1
						then
							032B: $BONUS_GUN3 = create_weapon_pickup #GRENADE 14 ammo 10 at -646.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_SHOTGUN == 1
						then
							032B: $BONUS_GUN4 = create_weapon_pickup #SHOTGUN 14 ammo 50 at -644.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_ARMOUR == 1
						then
							0213: $BONUS_GUN5 = create_pickup #BODYARMOUR type 14 at -642.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_MOLOTOVS == 1
						then
							032B: $BONUS_GUN6 = create_weapon_pickup #MOLOTOV 14 ammo 10 at -640.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_AK47 == 1
						then
							032B: $BONUS_GUN7 = create_weapon_pickup #AK47 14 ammo 300 at -638.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_SNIPER == 1
						then
							032B: $BONUS_GUN8 = create_weapon_pickup #SNIPER 14 ammo 50 at -636.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_M16 == 1
						then
							032B: $BONUS_GUN9 = create_weapon_pickup #M16 14 ammo 600 at -634.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_FLAMETHROWER == 1
						then
							032B: $BONUS_GUNFLAME = create_weapon_pickup #FLAME 14 ammo 2000 at -632.5 -24.75 18.75 
						end
						if
							$EARNED_FREE_LAUNCHER == 1
						then
							032B: $BONUS_GUN10 = create_weapon_pickup #ROCKET 14 ammo 50 at -630.5 -24.75 18.75 
						end
						if
							$IND_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE1 = create_pickup #BRIBE type 14 at -663.0 -28.0 18.25 
						end
						if
							$IND_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE2 = create_pickup #BRIBE type 14 at -665.0 -28.0 18.25 
						end
						if
							$COM_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE3 = create_pickup #BRIBE type 14 at -667.0 -28.0 18.25 
						end
						if
							$COM_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE4 = create_pickup #BRIBE type 14 at -669.0 -28.0 18.25
						end
						if
							$SUB_COPCAR_KILLS > 9
						then
							0213: $BONUS_BRIBE5 = create_pickup #BRIBE type 14 at -671.0 -28.0 18.25
						end
						if
							$SUB_COPCAR_KILLS > 19
						then
							0213: $BONUS_BRIBE6 = create_pickup #BRIBE type 14 at -673.0 -28.0 18.25 
						end
						if
							$TOTAL_SAVED_PEDS > 34
						then
							0213: $BONUS_HEALTH = create_pickup #HEALTH type 14 at -654.25 -21.6875 18.25 
						end
						if
							$TOTAL_SAVED_PEDS > 69
						then
							0213: $BONUS_ADRENALINE = create_pickup #ADRENALINE type 14 at -654.25 -19.6875 18.25 
						end
						$BONUS_WEAPONS_CREATED = 1
					end
				else	//	player is not in area (-595.8 -68.2 10.0 -700.7 3.1 30.0)
					if
						$BONUS_WEAPONS_CREATED == 1
					then
						gosub @REMOVE_BONUS_PICKUPS
					end
				end
			else	//	player is not in PROJECT
				if
					$BONUS_WEAPONS_CREATED == 1
				then
					gosub @REMOVE_BONUS_PICKUPS
				end
			end
		end
	end
end

:REMOVE_BONUS_PICKUPS
0215: destroy_pickup $HIDEOUT_BAT
0215: destroy_pickup $BONUS_GUN1 
0215: destroy_pickup $BONUS_GUN2 
0215: destroy_pickup $BONUS_GUN3 
0215: destroy_pickup $BONUS_GUN4 
0215: destroy_pickup $BONUS_GUN5 
0215: destroy_pickup $BONUS_GUN6 
0215: destroy_pickup $BONUS_GUN7 
0215: destroy_pickup $BONUS_GUN8 
0215: destroy_pickup $BONUS_GUN9 
0215: destroy_pickup $BONUS_GUNFLAME 
0215: destroy_pickup $BONUS_GUN10 
0215: destroy_pickup $BONUS_BRIBE1 
0215: destroy_pickup $BONUS_BRIBE2 
0215: destroy_pickup $BONUS_BRIBE3 
0215: destroy_pickup $BONUS_BRIBE4 
0215: destroy_pickup $BONUS_BRIBE5 
0215: destroy_pickup $BONUS_BRIBE6 
0215: destroy_pickup $BONUS_HEALTH 
0215: destroy_pickup $BONUS_ADRENALINE 
0004: $BONUS_WEAPONS_CREATED = 0  
return



