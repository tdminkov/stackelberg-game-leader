import comp34120.ex2.Record;
import java.util.List;
public interface Model{

  float getA();

  float getB();

  void train(List<Record> records);

  float predict(float u);
}
