import comp34120.ex2.Record;
import java.util.ArrayList;
import java.util.List;

public class SimpleModel implements Model{
  private float a;
  private float b;
  private int windowSize;

  public SimpleModel() {
    a = 1.0f;
    b = 1.0f;
    windowSize = 50;
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

      sum_leader += record.m_leaderPrice;
      sum_squared_leader += record.m_leaderPrice * record.m_leaderPrice;
      sum_follower += record.m_followerPrice;
      sum_leader_times_follower += record.m_leaderPrice * record.m_followerPrice;
    }
    // System.out.println("Sum Leader: " + sum_leader);
    // System.out.println("Sum Squared Leader: " + sum_squared_leader);
    // System.out.println("Sum Leader Times Follower: " + sum_leader_times_follower);
    // System.out.println("Sum Follower: " + sum_follower);
    // System.out.println("Record count: " + records.size());
    a = (sum_squared_leader * sum_follower
    		- sum_leader * sum_leader_times_follower)
    		/ (windowSize * sum_squared_leader - (sum_leader*sum_leader));

    b = (windowSize * sum_leader_times_follower
    		- sum_leader * sum_follower)
    		/ (windowSize * sum_squared_leader - (sum_leader * sum_leader));
    // - sum of leader price * sum of follower reaction
    // / number of data points * sum of squared leader price - sum of leader price squared


  }

  @Override
  public float predict(float u) {
    return a + b * u;
  }
}
