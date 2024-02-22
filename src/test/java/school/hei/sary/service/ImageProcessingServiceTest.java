package school.hei.sary.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import school.hei.sary.exception.BadRequestException;
import school.hei.sary.service.event.ImageProcessingService;

class ImageProcessingServiceTest {
  public static byte[] getFileAsClassPathResource(String path) throws IOException {
    File file = new ClassPathResource(path).getFile();
    return FileUtils.readFileToByteArray(file);
  }

  @Test
  void should_return_valid_byte_if_correct_image() throws IOException {
    ImageProcessingService imageProcessingService = new ImageProcessingService();
    byte[] inputImage = getFileAsClassPathResource("mock/mock_image.jpg");
    String key = "someKey";
    byte[] result = imageProcessingService.convertToBlackAndWhite(inputImage, key);

    assertNotNull(result);
  }

  @Test
  void should_throws_bad_request_on_wrong_request_body() {
    ImageProcessingService imageProcessingService = new ImageProcessingService();
    byte[] invalidInputImage = {4, 4};
    String key = "someKey";

    assertThrows(
        BadRequestException.class,
        () -> imageProcessingService.convertToBlackAndWhite(invalidInputImage, key));
  }
}
