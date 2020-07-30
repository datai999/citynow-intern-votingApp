package model.dao.service.admin;

import model.dto.poll.PollBuilder;
import model.dao.service.BaseDao;
import model.dao.IAdminService;
import model.dto.poll.Poll;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class CreatePollService extends BaseDao implements IAdminService {

    Logger _logger;

    private CreatePollService(){
        super();
        _logger = Logger.getLogger(this.getClass().getName());
    }
    private static class LazyHolder{
        public static final CreatePollService INSTANCE = new CreatePollService();
    }

    public static CreatePollService getInstance(){
        return CreatePollService.LazyHolder.INSTANCE;
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
    public boolean createPoll(int userId, String strDeadline, String tittle, String question, String[] options){

//        insert question to database
        String insertQuestion = "INSERT INTO poll (userId, timeCreate, timeStart, timeEnd, viewRole, minVote, maxVote, title, question) VALUES (?,?,?,?,?,?,?,?,?)";
        int deadline = convert(strDeadline);
        Poll poll = new PollBuilder().buildBase(userId, deadline, tittle, question, options).build();
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
