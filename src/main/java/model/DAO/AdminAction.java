package model.DAO;

import database.MySQLConnection;
import model.DTO.Poll;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AdminAction {

    Logger _logger;

    private AdminAction(){
        _logger = Logger.getLogger(this.getClass().getName());
    }
    private static class LazyHolder{
        public static final AdminAction INSTANCE = new AdminAction();
    }
    public static AdminAction getInstance(){
        return AdminAction.LazyHolder.INSTANCE;
    }



    int convert(String strDeadline){

        strDeadline = strDeadline.substring(0,10) + "+" + strDeadline.substring(11);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd+HH:mm");

        Date date = null;
        try {
            date = dateFormat.parse(strDeadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert date != null;
        return (int) (date.getTime()/1000);
    }


    public boolean createQuestion(int userId, String strDeadline, String tittle, String question, String[] options){

        int deadline = convert(strDeadline);

        Poll poll = new Poll(userId, deadline, tittle, question, options);

        String insertQuestion = "INSERT INTO poll (user_id, time_create, time_deadline, title, question) VALUES (?, ?, ?, ?, ?)";
        String queryQuestion = "SELECT id FROM poll WHERE user_id=? AND time_create=?";
        String queryOption = "INSERT INTO poll_option (poll_id, content) VALUES (?, ?),(?, ?),(?, ?),(?, ?)";


//        insert question to database
        List<Object> paramsQuestion = Arrays.asList(poll.getArrObj());
        MySQLConnection connection1 = new MySQLConnection();
        int countQuestion = connection1.execute(insertQuestion, paramsQuestion);
        if (countQuestion < 1) return false;


//        query id question
        AtomicInteger id = new AtomicInteger();
        MySQLConnection connection2 = new MySQLConnection();
        connection2.execute(queryQuestion, Arrays.asList(userId, paramsQuestion.get(1)), rs -> {
            try {
                if (rs.next()) {
                    id.set(rs.getInt("id"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        poll.setId(id.get());

//        insert option to database
        List<Object> paramsOption = Arrays.asList(poll.getArrOptionObj());
        MySQLConnection databaseOption = new MySQLConnection();
        int countOption = databaseOption.execute(queryOption, paramsOption);


        return countOption>0;
    }
}
