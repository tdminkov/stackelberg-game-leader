import comp34120.ex2.Record;
import java.util.ArrayList;
import java.util.List;

public class Model {
  private float a;
  private float b;

  public Model() {
    a = 1.0f;
    b = 1.0f;
  }

  public float getA() {
    return a;
  }

  public float getB() {
    return b;
  }

  public void train(List<com.sun.prism.impl.Disposer.Record> records) {
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
    for (Record record : records) {
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

    a = sum_squared_leader * sum_follower
    		- sum_leader * sum_leader_times_follower
    		/ (records.size() * sum_squared_leader - (sum_leader*sum_leader));

    b = records.size() * sum_leader_times_follower
    		- sum_leader * sum_follower
    		/ (records.size() * sum_squared_leader - (sum_leader * sum_leader));
    // - sum of leader price * sum of follower reaction
    // / number of data points * sum of squared leader price - sum of leader price squared


  }

  public float predict(float u) {
    return a + b * u;
  }
}
