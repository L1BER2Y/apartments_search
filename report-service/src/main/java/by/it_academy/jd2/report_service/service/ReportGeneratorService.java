package by.it_academy.jd2.report_service.service;

import by.it_academy.jd2.report_service.core.entity.AuditEntity;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.service.api.IReportGenerator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Qualifier("excel-file-generator")
public class ReportGeneratorService implements IReportGenerator {

    private final String[] headers = {
            "id",
            "action_date",
            "user_id",
            "user_email",
            "user_fio",
            "user_role",
            "action",
            "essence_type",
            "essence_type_id"
    };

    @Override
    public void generate(List<ReportEntity> reports, UUID name) {

    }

    private void createHeader(XSSFSheet spreadsheet) {
        createRow(spreadsheet, 0, headers);
    }

    private void createRow(XSSFSheet spreadsheet, int rowId, Object[] objectArray) {
        XSSFRow row = spreadsheet.createRow(rowId);
        int cellId = 0;
        for (Object object : objectArray) {
            Cell cell = row.createCell(cellId);
            cell.setCellValue((String) object);
            cellId++;
        }
    }

    private Object[] convertToArray(AuditEntity audit) {
        return new Object[] {
                audit.getId().toString(),
                audit.getMail(),
                audit.getFio(),
                audit.getRole().toString(),
                audit.getEssenceType().toString()
        };
    }
}
