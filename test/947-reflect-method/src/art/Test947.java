/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package art;

import java.util.Base64;
import java.lang.reflect.Method;

public class Test947 {

  static class Transform {
    public void sayHi() {
      System.out.println("hello");
    }
  }


  /**
   * base64 encoded class/dex file for
   * static class Transform {
   *   public void sayHi() {
   *    System.out.println("Goodbye");
   *   }
   * }
   */
  private static final byte[] CLASS_BYTES = Base64.getDecoder().decode(
    "yv66vgAAADQAIAoABgAOCQAPABAIABEKABIAEwcAFQcAGAEABjxpbml0PgEAAygpVgEABENvZGUB" +
    "AA9MaW5lTnVtYmVyVGFibGUBAAVzYXlIaQEAClNvdXJjZUZpbGUBAAxUZXN0OTQ3LmphdmEMAAcA" +
    "CAcAGQwAGgAbAQAHR29vZGJ5ZQcAHAwAHQAeBwAfAQAVYXJ0L1Rlc3Q5NDckVHJhbnNmb3JtAQAJ" +
    "VHJhbnNmb3JtAQAMSW5uZXJDbGFzc2VzAQAQamF2YS9sYW5nL09iamVjdAEAEGphdmEvbGFuZy9T" +
    "eXN0ZW0BAANvdXQBABVMamF2YS9pby9QcmludFN0cmVhbTsBABNqYXZhL2lvL1ByaW50U3RyZWFt" +
    "AQAHcHJpbnRsbgEAFShMamF2YS9sYW5nL1N0cmluZzspVgEAC2FydC9UZXN0OTQ3ACAABQAGAAAA" +
    "AAACAAAABwAIAAEACQAAAB0AAQABAAAABSq3AAGxAAAAAQAKAAAABgABAAAABQABAAsACAABAAkA" +
    "AAAlAAIAAQAAAAmyAAISA7YABLEAAAABAAoAAAAKAAIAAAAHAAgACAACAAwAAAACAA0AFwAAAAoA" +
    "AQAFABQAFgAI");
  private static final byte[] DEX_BYTES = Base64.getDecoder().decode(
    "ZGV4CjAzNQCEgoKcAAAAAAAAAAAAAAAAAAAAAAAAAAC4AwAAcAAAAHhWNBIAAAAAAAAAAPQCAAAU" +
    "AAAAcAAAAAkAAADAAAAAAgAAAOQAAAABAAAA/AAAAAQAAAAEAQAAAQAAACQBAAB0AgAARAEAAEQB" +
    "AABMAQAAVQEAAG4BAAB9AQAAoQEAAMEBAADYAQAA7AEAAAACAAAUAgAAIgIAAC0CAAAwAgAANAIA" +
    "AEECAABHAgAATAIAAFUCAABcAgAAAgAAAAMAAAAEAAAABQAAAAYAAAAHAAAACAAAAAkAAAAMAAAA" +
    "DAAAAAgAAAAAAAAADQAAAAgAAABkAgAABwAEABAAAAAAAAAAAAAAAAAAAAASAAAABAABABEAAAAF" +
    "AAAAAAAAAAAAAAAAAAAABQAAAAAAAAAKAAAA5AIAALgCAAAAAAAABjxpbml0PgAHR29vZGJ5ZQAX" +
    "TGFydC9UZXN0OTQ3JFRyYW5zZm9ybTsADUxhcnQvVGVzdDk0NzsAIkxkYWx2aWsvYW5ub3RhdGlv" +
    "bi9FbmNsb3NpbmdDbGFzczsAHkxkYWx2aWsvYW5ub3RhdGlvbi9Jbm5lckNsYXNzOwAVTGphdmEv" +
    "aW8vUHJpbnRTdHJlYW07ABJMamF2YS9sYW5nL09iamVjdDsAEkxqYXZhL2xhbmcvU3RyaW5nOwAS" +
    "TGphdmEvbGFuZy9TeXN0ZW07AAxUZXN0OTQ3LmphdmEACVRyYW5zZm9ybQABVgACVkwAC2FjY2Vz" +
    "c0ZsYWdzAARuYW1lAANvdXQAB3ByaW50bG4ABXNheUhpAAV2YWx1ZQAAAQAAAAYAAAAFAAcOAAcA" +
    "Bw4BCA8AAAAAAQABAAEAAABsAgAABAAAAHAQAwAAAA4AAwABAAIAAABxAgAACQAAAGIAAAAbAQEA" +
    "AABuIAIAEAAOAAAAAAABAQCAgAT8BAEBlAUAAAICARMYAQIDAg4ECA8XCwACAAAAyAIAAM4CAADY" +
    "AgAAAAAAAAAAAAAAAAAAEAAAAAAAAAABAAAAAAAAAAEAAAAUAAAAcAAAAAIAAAAJAAAAwAAAAAMA" +
    "AAACAAAA5AAAAAQAAAABAAAA/AAAAAUAAAAEAAAABAEAAAYAAAABAAAAJAEAAAIgAAAUAAAARAEA" +
    "AAEQAAABAAAAZAIAAAMgAAACAAAAbAIAAAEgAAACAAAAfAIAAAAgAAABAAAAuAIAAAQgAAACAAAA" +
    "yAIAAAMQAAABAAAA2AIAAAYgAAABAAAA5AIAAAAQAAABAAAA9AIAAA==");

  public static void run() {
    doTest(new Transform());
  }

  public static void doTest(Transform t) {
    try {
      Method say_hi_method = t.getClass().getDeclaredMethod("sayHi");
      say_hi_method.invoke(t);
      Redefinition.doCommonClassRedefinition(Transform.class, CLASS_BYTES, DEX_BYTES);
      say_hi_method.invoke(t);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
