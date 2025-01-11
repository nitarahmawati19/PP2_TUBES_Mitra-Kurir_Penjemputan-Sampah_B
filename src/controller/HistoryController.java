package controller;



import model.PickupHistory;
import model.PickupHistoryMapper;
import view.HistoryFrame;
import view.PickupHistoryPdf;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class HistoryController {
    private HistoryFrame historyFrame;
    private SqlSessionFactory sqlSessionFactory;
    private PickupHistoryPdf pdfExporter;

    public HistoryController(HistoryFrame historyFrame) {
        this.historyFrame = historyFrame;
        this.pdfExporter = new PickupHistoryPdf();
        initializeMyBatis();
        initController();
    }

    private void initializeMyBatis() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initController() {
        historyFrame.getRefreshButton().addActionListener(e -> loadData());
        historyFrame.getCategoryDropdown().addActionListener(e -> loadData());
        historyFrame.getDownloadPdfButton().addActionListener(new ExportPdfListener());
        loadData();
    }

    private void loadData() {
        String selectedCategory = (String) historyFrame.getCategoryDropdown().getSelectedItem();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PickupHistoryMapper mapper = session.getMapper(PickupHistoryMapper.class);
            List<PickupHistory> historyList = mapper.getAllPickupHistoriesByCategory(selectedCategory);
            historyFrame.setTableData(historyList);
        }
    }

    class ExportPdfListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try (SqlSession session = sqlSessionFactory.openSession()) {
                PickupHistoryMapper mapper = session.getMapper(PickupHistoryMapper.class);
                List<PickupHistory> historyList = mapper.getAllPickupHistories();
                pdfExporter.exportPdf(historyList);
            }
        }
    }
}