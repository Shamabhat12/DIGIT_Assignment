package digit.academy.tutorial.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Workflow {
    private String action;
    private List<String> assignees;
    private String comments;
    private List<Document> documents;
}