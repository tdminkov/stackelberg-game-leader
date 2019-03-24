import comp34120.ex2.Record;
import java.util.ArrayList;
import java.util.List;

public class Model {
  private double a;
  private double b;

  public void train(List<Record> records) {
    //TODO
    // a
    // sum of squared leader price * sum of follower reaction
    // - sum of leader price * sum of (leader price * follower reaction)
    // / number of data points * sum of squared leader price - sum of leader price squared
    float sum_squared_leader = 0f;
    float sum_leader = 0f;
    float sum_leader_times_follower = 0f;
    float sum_follower = 0f;
    for (Record record : records) {
    }


    // b
    // number of data points * sum of (leader price * follower reaction)
    // - sum of leader price * sum of follower reaction
    // / number of data points * sum of squared leader price - sum of leader price squared


  }

  public double predict(double u) {
    return a + b * u;
  }
}
