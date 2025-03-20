/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jdbcassignment;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author Bonif
 */
public class SongDAO {

    //getConnection
    // this code returns the Connection to an SQL database. 
    // Connections are needed to send and recieve data
    public Connection getConnection() {
        Connection conn = null;

        try {

            //declares the drrivers used for this connection attempt
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //string that cntains the location and login info for the database
            String connectionUrl = "jdbc:sqlserver://localhost:1434;DatabaseName=Songs; "
                    + "User=javaApps;Password=Java233";

            //store the connection using the url in conn
            conn = DriverManager.getConnection(connectionUrl);

        } catch (Exception e) {
            System.out.println("An error occured when trying to connect to the database");
            e.printStackTrace();
        }
        return conn;
    }

    //returns an array list contaning all songs in the database
    public ArrayList getSongs() {

        //create variable to return to user
        ArrayList songs = new ArrayList();

        //establish a conneciton to the database
        Connection conn = getConnection();

        //create a resultset to store the data from our query
        ResultSet rs = null;
        try {
            //create an sql statement
            Statement stmt = conn.createStatement();

            //send an sql query to get the data we need
            //and store the returned data in our result set
            rs = stmt.executeQuery("SELECT id, title, artist, releaseYear, genreId, album FROM Songs");

            //parse the data that come sback into our arraylist
            while (rs.next()) {
                //create a Song object to store a single recors
                Song s = new Song(Integer.parseInt(rs.getString(1)),
                        rs.getString(2),
                        rs.getString(3),
                        Integer.parseInt(rs.getString(4)),
                        Integer.parseInt(rs.getString(5)),
                        rs.getString(6));

// OTHER METHOD:
//             int id = Integer.parseInt(rs.getString(1));
//             String artist = rs.getString(2);   
//             String title = rs.getString(3);
//             int releaseYear = Integer.parseInt(rs.getString(4));    
//             int genreId = Integer.parseInt(rs.getString(5));   
//             String album = rs.getString(6);
//             Song s = new Song(id, artist, title, releaseYear, genreId, album); 
                //function to add songs to array list
                songs.add(s);
            }

            conn.close();

        } catch (SQLException sqle) {
            System.out.println("There was a problem with the SQL trying to get songs");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occured when trying to connect to the database");
            e.printStackTrace();
        }
        
        //return songs to user
        return songs;
    }
    
    //returns an array list contaning all songs in the database
    public Song getSongById(int id) {

        //create variable to return to user
        Song s = new Song();

        //establish a conneciton to the database
        Connection conn = getConnection();

        //create a resultset to store the data from our query
        ResultSet rs = null;
        try 
        {
            //create an sql statement
            Statement stmt = conn.createStatement();
            
            //send an sql query to get the data we need
            //and store the returned data in our result set
            String query = "SELECT id, title, artist, releaseYear, genreId, album FROM Songs WHERE id =" + id;
            
            rs = stmt.executeQuery(query);
            
            if(rs.next()){

            //parse the data that come sback into our arraylist
              int songId = Integer.parseInt(rs.getString(1));
              String artist = rs.getString(2);   
              String title = rs.getString(3);
              int releaseYear = Integer.parseInt(rs.getString(4));    
              int genreId = Integer.parseInt(rs.getString(5));   
              String album = rs.getString(6);
              
              s = new Song(songId, artist, title, releaseYear, genreId, album);  
            }
             //close connection to server
             conn.close();
            

        } catch (SQLException sqle) {
            System.out.println("There was a problem with the SQL trying to get songs");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occured when trying to connect to the database");
            e.printStackTrace();
        }
        //return songs to user
        return s;
    }


    //accepts a song object, reads the id value, then updates the database
    //item for that song id with the other info from the passed in song
    public void updateSongById(Song s)
    {
        Connection conn = getConnection();
        
        try
        {
            //make a statment
            Statement stmt = conn.createStatement();

//            //create our update sql query
//            String updateQuery = "UPDATE Songs SET title = '"+s.getTitle() +
//                        "', Artist = '" + s.getArtist() +
//                        "', Album = '" + s.getAlbum() +
//                        "' WHERE Id=" + s.getId();
            String update = String.format("""
                                          UPDATE Songs SET Title = '%s',
                                          Artist = '%s',
                                          Album = '%s',
                                          WHERE id = %d""", s.getTitle(), s.getArtist(),
                                          s.getAlbum(), s.getId());

            //send our update sql query
            stmt.executeUpdate(update);
           
        
        //close connection to server
        conn.close();

        }catch(SQLException sqle)
        {
            System.out.println("An issure occured with the SQL when trying to update a song");
            sqle.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("An issue occured when trying to update a song");
            e.printStackTrace();
        }
        
    }
    
    public void deleteSongById(int id)
    {
        //establish connection
        Connection conn = getConnection();
        
        try
        {
            //create a statment
            Statement stmt = conn.createStatement();
            
            //write a sql delete statement
            String delete = "DELETE FROM Songs WHERE id = "+ id;
            
            //execute the statement
            stmt.executeUpdate(delete);
            
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("There was an error with the SQL when deleting song");
            sqle.printStackTrace();
            
        }
        catch(Exception e){
            System.out.println("there was an issue occuring when deleting a song");
            e.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        SongDAO dao = new SongDAO();
        Scanner input = new Scanner(System.in);
        
//        System.out.println("Enter an id to delete: ");
//        int id = input.nextInt();
//        
//                dao.deleteSongById(id);
//        
        

        System.out.println("Enter an id to search: ");
        int id = input.nextInt();
        //get that songs info
        Song s = dao.getSongById(id);
        System.out.println("------------");
        System.out.println(s);


//        //get the id of the song to edit
//        System.out.print("Enter a song Id to edit: ");
//        int id = input.nextInt();

//        //get the new information
//        System.out.print("Enter a new artist: ");
//        String artist = input.next();
//        
//        //update the song objext with the new info
//        s.setArtist(artist);
//
//        //send the data to the database
//        dao.updateSongById(s);
//



     //ArrayList songs = dao.getSongs();
//       for (int i = 0; i < songs.size(); i++) {
//            System.out.println("-------------");
//            System.out.println(songs.get(i));
//        }
        
//        ArrayList songs = dao.getSongs();
//        for (int i = 0; i < songs.size(); i++) {
//            Song song = (Song) songs.get(i); // Cast to Song
//            System.out.println("-------------");
//            System.out.println(song.getFullInfo()); // Call getFullInfo()
//        }
    }
}

