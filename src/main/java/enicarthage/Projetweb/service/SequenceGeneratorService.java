package Services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    private final JdbcTemplate jdbcTemplate;

    public SequenceGeneratorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int generateSequence(String seqName) {
        String query = "SELECT seq FROM sequences WHERE seq_name = ?";
        Integer sequence = jdbcTemplate.queryForObject(query, Integer.class, seqName);
        
        if (sequence != null) {
            String updateQuery = "UPDATE sequences SET seq = seq + 1 WHERE seq_name = ?";
            jdbcTemplate.update(updateQuery, seqName);
            return sequence;
        } else {
            String insertQuery = "INSERT INTO sequences (seq_name, seq) VALUES (?, 1)";
            jdbcTemplate.update(insertQuery, seqName);
            return 1;
        }
    }
}

