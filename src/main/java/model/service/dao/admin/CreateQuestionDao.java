package model.service.dao.admin;

import model.service.dao.BaseDao;
import model.service.IAdminService;
import model.dto.Poll;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class CreateQuestionDao extends BaseDao implements IAdminService {

    Logger _logger;

    private CreateQuestionDao(){
        super();
        _logger = Logger.getLogger(this.getClass().getName());
    }
    private static class LazyHolder{
        public static final CreateQuestionDao INSTANCE = new CreateQuestionDao();
    }

    public static CreateQuestionDao getInstance(){
        return CreateQuestionDao.LazyHolder.INSTANCE;
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

    @Override
    public boolean createQuestion(int userId, String strDeadline, String tittle, String question, String[] options){

//        insert question to database
        String insertQuestion = "INSERT INTO poll (userId, timeCreate, timeStart, timeEnd, title, question) VALUES (?, ?, ?, ?, ?, ?)";
        int deadline = convert(strDeadline);
        Poll poll = new Poll(userId, deadline, tittle, question, options);
        List<Object> paramsQuestion = Arrays.asList(poll.getArrObj());

        int countQuestion = execute(insertQuestion, paramsQuestion);
        if (countQuestion < 1) return false;



//        query id question
        String queryQuestion = "SELECT id FROM poll WHERE userId=? AND timeCreate=?";
        AtomicInteger id = new AtomicInteger();
        execute(queryQuestion, Arrays.asList(userId, paramsQuestion.get(1)), rs -> {
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
        String queryOption = "INSERT INTO poll_option (pollId, content) VALUES (?, ?),(?, ?),(?, ?),(?, ?)";
        List<Object> paramsOption = Arrays.asList(poll.getArrOptionObj());
        int countOption = execute(queryOption, paramsOption);


        return countOption>0;
    }
}
