package model;

import java.util.List;

public interface PickupHistoryMapper {
    List<PickupHistory> getAllPickupHistories();
    List<PickupHistory> getAllPickupHistoriesByCategory(String category);
}