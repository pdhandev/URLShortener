import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static spark.Spark.post;
import static spark.Spark.get;

public class Shorty {
  public static void main(String[] args) {
    ToImplement impl = new ToImplement();

    // curl http://localhost:4567/a
    get("/:short", (request, response) -> {
      return impl.get(request.params(":short"));
    });

    // curl -d "{'url'='http://foo.tld'}" http://localhost:4567
    post("/", (request, response) -> {
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse(request.body());
      JsonObject object = element.getAsJsonObject();
      String longURL = object.get("url").toString().replace("\"", "");

      String uri = impl.set(longURL);

      return "{'short':'" + uri + "'}";
    });
  }
}
