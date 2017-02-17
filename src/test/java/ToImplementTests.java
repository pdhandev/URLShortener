import org.junit.Test;

public class ToImplementTests {
  @Test
  public void oneURL() {
    ToImplement impl = new ToImplement();
    String testURL = "http://foo.tld";
    String expected = "a";

    String shortURI = impl.set(testURL);
    assert shortURI.equals(expected);

    String longURI = impl.get(shortURI);
    assert longURI.equals(testURL);
  }

  @Test
  public void boundary() {
    ToImplement impl = new ToImplement();
    String testURL = "http://foo.tld";
    String expected = "ab";

    for (int i = 1; i <= 63; i++) {
      impl.set(testURL);
    }

    String shortURI = impl.set(testURL);
    assert shortURI.equals(expected);

    String longURI = impl.get(shortURI);
    assert longURI.equals(testURL);
  }
}
