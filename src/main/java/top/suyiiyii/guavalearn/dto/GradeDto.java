package top.suyiiyii.guavalearn.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeDto {
    @NotBlank
    @Size(min = 1, max = 10)
    private String studentid;
    @NotBlank
    @Size(min = 1, max = 1000)
    private int grade;
}
