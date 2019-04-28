import comp34120.ex2.Record;
import java.util.ArrayList;
import java.util.List;

public class ForgettingModel implements Model{
  private float a;
  private float b;
  private int windowSize;
  private float lambda;

  public ForgettingModel() {
    a = 1.0f;
    b = 1.0f;
    lambda = 0.97f;
    windowSize = 16;
  }

  @Override
  public float getA() {
    return a;
  }
  @Override
  public float getB() {
    return b;
  }

  @Override
  public void train(List<Record> records) {
    //TODO
    // a
    // sum of squared leader price * sum of follower reaction
    // - sum of leader price * sum of (leader price * follower reaction)
    // / number of data points * sum of squared leader price - sum of leader price squared

    // b
    // number of data points * sum of (leader price * follower reaction)
    // - sum of leader price * sum of follower reaction
    // / number of data points * sum of squared leader price - sum of leader price squared
    float sum_squared_leader = 0f;
    float sum_leader = 0f;
    float sum_leader_times_follower = 0f;
    float sum_follower = 0f;
    float sum_lambda = 0f;
    for (int i = records.size() - windowSize; i < records.size(); i++) {
      Record record = records.get(i);
      /*
      // The date of the record
    	public final int m_date;
    	// The price of the leader
    	public final float m_leaderPrice;
    	// The price of the follower
    	public final float m_followerPrice;
      // The cost of the follower
    	public final float m_cost; */

      float forgettingFactor = (float) Math.pow(lambda, windowSize - i);

      sum_lambda += forgettingFactor;
      sum_leader += forgettingFactor * record.m_leaderPrice; // sum(x(t))
      sum_squared_leader += forgettingFactor * record.m_leaderPrice * record.m_leaderPrice; // sum(x(t)^2)
      sum_follower += forgettingFactor * record.m_followerPrice; // sum(y(t))
      sum_leader_times_follower += forgettingFactor * record.m_leaderPrice * record.m_followerPrice; // sum(x(t)y(t))
    }
    // System.out.println("Sum Leader: " + sum_leader);
    // System.out.println("Sum Squared Leader: " + sum_squared_leader);
    // System.out.println("Sum Leader Times Follower: " + sum_leader_times_follower);
    // System.out.println("Sum Follower: " + sum_follower);
    // System.out.println("Record count: " + records.size());
    a = (sum_squared_leader * sum_follower
    		- sum_leader * sum_leader_times_follower)
    		/ (sum_lambda * sum_squared_leader - (sum_leader*sum_leader));

    b = (sum_lambda * sum_leader_times_follower
    		- sum_leader * sum_follower)
    		/ (sum_lambda * sum_squared_leader - (sum_leader * sum_leader));
    // - sum of leader price * sum of follower reaction
    // / number of data points * sum of squared leader price - sum of leader price squared


  }

  @Override
  public float predict(float u) {
    return a + b * u;
  }
}
