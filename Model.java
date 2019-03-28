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
      /*
      // The date of the record
    	public final int m_date;
    	// The price of the leader
    	public final float m_leaderPrice;
    	// The price of the follower
    	public final float m_followerPrice;
      // The cost of the follower
    	public final float m_cost; */
    }


    // b
    // number of data points * sum of (leader price * follower reaction)
    // - sum of leader price * sum of follower reaction
    // / number of data points * sum of squared leader price - sum of leader price squared


  }

  public float predict(float u) {
    return a + b * u;
  }
}
