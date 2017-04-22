package project.agile.DataAnalysis;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReadCSV {
	public static final String PLAYER = "Player";
	public static final String BIRTH = "Birth";
	public static final String SEASON = "Season";
	public static final String LEAGUE = "Lg";
	public static final String TEAMABBR = "TeamAbbr";
	public static final String GAMENUM = "G";
	public static final String PTS = "PTS";
	public static final String TEAMNAME = "TeamName";
	public static final String TEAMFROM = "TeamFrom";
	public static final String TEAMTO = "TeamTo";
	public static final String TEAMG = "TeamG";
	public static final String TEAMW = "TeamW";
	public static final String TEAML = "TeamL";
	public static final String TEAMCHAMP = "TeamChamp";
	public static final String TEAMCOACH = "TeamCoach";
	public static final String TEAMSEASON = "TeamSeason";
	public static final String ARENA = "Arena";
	public static final String ARENASTART = "ArenaStart";
	public static final String ARENAEND = "ArenaEnd";
	public static final String ARENALOCATION = "ArenaLocation";
	public static final String ARENACAPACITY = "ArenaCapacity";

	public static final String filePath = android.os.Environment.getExternalStorageDirectory()+"/project.agile.nbaapp";

	Map<List<String>, List<String>> player;
	Map<List<String>, List<String>> team;
	Map<List<String>, List<String>> coach;
	Map<List<String>, List<String>> arena;

	/*public void readFile(SQLiteDatabase db, String fileName){
		try {
			ContentValues values = new ContentValues();
			CSVReader reader;
			// 读取球队信息并存入数据库
			reader = new CSVReader(new FileReader(filePath + "/" + fileName));
			String[] nextLine;
			nextLine = reader.readNext();
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				values.put(LEAGUE, nextLine[5]);
				values.put(TEAMABBR, nextLine[4]);
				values.put(TEAMNAME, nextLine[10]);
				values.put(TEAMFROM, nextLine[12]);
				values.put(TEAMTO, nextLine[13]);
				values.put(TEAMG, nextLine[15]);
				values.put(TEAMW, nextLine[16]);
				values.put(TEAML, nextLine[17]);
				values.put(TEAMCHAMP, nextLine[18]);
				db.beginTransaction();
				try {
					db.insert("Team", null, values);
					db.setTransactionSuccessful();
				}catch (Exception e){
					return ;
				}finally {
					db.endTransaction();
				}
				values.clear();
			}

			// 读取其他信息并存入数据库
			reader = new CSVReader(new FileReader(filePath + "/" + fileName));
			nextLine = reader.readNext();
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				String[] seasonStr = nextLine[2].split("-");
				double season = Double.parseDouble(seasonStr[1]);
				double age = (nextLine[3].matches("\\d+")) ? Double.parseDouble(nextLine[3]) : season - 1;

				// if age is null, then birth year is 0
				double birthYear = season - age - 1;

				values.put(PLAYER,nextLine[1]);
				values.put(BIRTH,birthYear+"");
				values.put(SEASON,seasonStr[1]);
				values.put(LEAGUE,nextLine[5]);
				values.put(TEAMABBR,nextLine[4]);
				values.put(GAMENUM,nextLine[6]);
				values.put(PTS,nextLine[7]);
				db.beginTransaction();
				try {
					db.insert("Player", null, values);
					db.setTransactionSuccessful();
				}catch (Exception e){
					return ;
				}finally {
					db.endTransaction();
				}
				values.clear();

				String[] str = nextLine[11].split("  ");
				for (String s : str) {
					values.put(TEAMCOACH,s);
					values.put(TEAMSEASON,seasonStr[1]);
					values.put(TEAMABBR,nextLine[5]);
					values.put(LEAGUE,nextLine[4]);
					db.beginTransaction();
					try {
						db.insert("Coach", null, values);
						db.setTransactionSuccessful();
					}catch (Exception e){
						return ;
					}finally {
						db.endTransaction();
					}
					values.clear();
				}

				String[] startEndStr = nextLine[2].split("-");
				if (nextLine[21].length() != 0) {
					values.put(ARENA,nextLine[21]);
					values.put(ARENASTART,startEndStr[0]);
					values.put(ARENAEND,startEndStr[1]);
					values.put(LEAGUE,nextLine[5]);
					values.put(TEAMABBR,nextLine[4]);
					values.put(ARENALOCATION,nextLine[22]);
					values.put(ARENACAPACITY,nextLine[23]);
					values.clear();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}*/

	public void readFile(String fileName) {
		try {
			CSVReader reader;
			reader = new CSVReader(new FileReader(filePath + "/" + fileName));
			team = ReadCSV.readCSVTeam(reader);

			reader = new CSVReader(new FileReader(filePath + "/" + fileName));
			player = ReadCSV.readCSVPlayer(reader);

			reader = new CSVReader(new FileReader(filePath + "/" + fileName));
			coach = ReadCSV.readCSVCoach(reader);
			
			reader = new CSVReader(new FileReader(filePath + "/" + fileName));
			arena = ReadCSV.readCSVArena(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	public void Insert(SQLiteDatabase db, String fileName){
		readFile(fileName);
		ContentValues values = new ContentValues();
		// traversal
		Set<List<String>> keys = team.keySet();
        for (List<String> la : keys) {
            List<String> lb = team.get(la);
            values.put(LEAGUE,la.get(0));
            values.put(TEAMABBR,la.get(1));
            values.put(TEAMNAME,lb.get(0));
            values.put(TEAMFROM,lb.get(1));
            values.put(TEAMTO,lb.get(2));
            values.put(TEAMG,lb.get(3));
            values.put(TEAMW,lb.get(4));
            values.put(TEAML,lb.get(5));
            values.put(TEAMCHAMP,lb.get(6));
            db.beginTransaction();
            try {
                db.insert("Team", null, values);
                db.setTransactionSuccessful();
            }catch (Exception e){
                return ;
            }finally {
                db.endTransaction();
            }
            values.clear();
        }

		// traversal
		keys.clear();
        keys = player.keySet();
        for (List<String> la : keys) {
            List<String> lb = player.get(la);
            values.put(PLAYER,la.get(0));
            values.put(BIRTH,la.get(1));
            values.put(SEASON,la.get(2));
            values.put(LEAGUE,lb.get(0));
            values.put(TEAMABBR,lb.get(1));
            values.put(GAMENUM,lb.get(2));
            values.put(PTS,lb.get(3));
            db.beginTransaction();
            try {
                db.insert("Player", null, values);
                db.setTransactionSuccessful();
            }catch (Exception e){
                return ;
            }finally {
                db.endTransaction();
            }
            values.clear();
        }


		// traversal
		keys.clear();
		keys = coach.keySet();
		for (List<String> la : keys) {
			List<String> lb = coach.get(la);
			values.put(TEAMCOACH,la.get(0));
			values.put(TEAMSEASON,la.get(1));
			values.put(TEAMABBR,lb.get(0));
			values.put(LEAGUE,lb.get(1));
            db.beginTransaction();
            try {
                db.insert("Coach", null, values);
                db.setTransactionSuccessful();
            }catch (Exception e){
                return ;
            }finally {
                db.endTransaction();
            }
            values.clear();
		}

		// traversal
		keys.clear();
		keys = arena.keySet();
		for (List<String> la : keys) {
			List<String> lb = arena.get(la);
			values.put(ARENA,la.get(0));
			values.put(ARENASTART,la.get(1));
			values.put(ARENAEND,la.get(2));
			values.put(LEAGUE,lb.get(0));
			values.put(TEAMABBR,lb.get(1));
			values.put(ARENALOCATION,lb.get(2));
			values.put(ARENACAPACITY,lb.get(3));
            db.beginTransaction();
            try {
			    db.insert("Arena",null,values);
                db.setTransactionSuccessful();
            }catch (Exception e){
                return ;
            }finally {
                db.endTransaction();
            }
			values.clear();
		}
	}
	
	public static Map<List<String>, List<String>> readCSVTeam(CSVReader reader) {
		
		String[] nextLine;
		
		// team
		Map<List<String>, List<String>> team = new HashMap<List<String>, List<String>>();
		
		try {
			nextLine = reader.readNext();
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
	//			System.out.println(nextLine[5] + " " + nextLine[4] + " " + nextLine[10] + " " + nextLine[12] + " " + nextLine[13] + " " + nextLine[15] + " " + nextLine[16] + " " + nextLine[17] + " " + nextLine[18]);
				List<String> la = new ArrayList<String>();
				la.add(nextLine[5]);
				la.add(nextLine[4]);
				if (team.get(la) == null) {
					List<String> lb = new ArrayList<String>();
					lb.add(nextLine[10]);
					lb.add(nextLine[12]);
					lb.add(nextLine[13]);
					lb.add(nextLine[15]);
					lb.add(nextLine[16]);
					lb.add(nextLine[17]);
					lb.add(nextLine[18]);
					
					team.put(la, lb);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return team;
	}
	
	public static Map<List<String>, List<String>> readCSVPlayer(CSVReader reader) {

		String[] nextLine;
		
		// player
		Map<List<String>, List<String>> player = new HashMap<List<String>, List<String>>();

		try {
			nextLine = reader.readNext();
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				String[] seasonStr = nextLine[2].split("-");
				double season = Double.parseDouble(seasonStr[1]);				
				double age = (nextLine[3].matches("\\d+")) ? Double.parseDouble(nextLine[3]) : season - 1;
	
				// if age is null, then birth year is 0
				double birthYear = season - age - 1;
	
				String name = nextLine[1];
				String league = nextLine[5];
				String attr = nextLine[4];
				double games = Double.parseDouble(nextLine[6]);
				double points = Double.parseDouble(nextLine[7]);
				
	//			System.out.println(name + " " + birthYear + " " + season + " " + league + " " + attr + " " + games + " " + points);
				List<String> la = new ArrayList<String>();
				la.add(name);
				la.add(birthYear + "");
				la.add(season + "");
				
				List<String> lb = new ArrayList<String>();
				lb.add(league);
				lb.add(attr);
				lb.add(games + "");
				lb.add(points + "");
				
				player.put(la, lb);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}
	
	public static Map<List<String>, List<String>> readCSVCoach(CSVReader reader) {

		String[] nextLine;
		
		// player
		Map<List<String>, List<String>> coach = new HashMap<List<String>, List<String>>();

		try {
			nextLine = reader.readNext();
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				String[] seasonStr = nextLine[2].split("-");
				double season = Double.parseDouble(seasonStr[1]);

				String[] str = nextLine[11].split("  ");
				for (String s : str) {
					if (s.length() != 0) {
						List<String> la = new ArrayList<String>();
						la.add(s);
						la.add(season + "");
						
						List<String> lb = new ArrayList<String>();
						lb.add(nextLine[5]);
						lb.add(nextLine[4]);

						coach.put(la, lb);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return coach;
	}
	
	public static Map<List<String>, List<String>> readCSVArena(CSVReader reader) {

		String[] nextLine;
		
		// player
		Map<List<String>, List<String>> arena = new HashMap<List<String>, List<String>>();

		try {
			nextLine = reader.readNext();
			nextLine = reader.readNext();
			while ((nextLine = reader.readNext()) != null) {
				String[] startEndStr = nextLine[2].split("-");
				double start = Double.parseDouble(startEndStr[0]);
				double end = Double.parseDouble(startEndStr[1]);

				List<String> la = new ArrayList<String>();
				la.add(nextLine[21]);
				la.add(start + "");
				la.add(end + "");
				if (nextLine[21].length() != 0) {
					if (arena.get(la) == null) {
						List<String> lb = new ArrayList<String>();
						lb.add(nextLine[5]);
						lb.add(nextLine[4]);
						
						lb.add(nextLine[22]);
						lb.add(nextLine[23]);
						
						arena.put(la, lb);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arena;
	}
}
