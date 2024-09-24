package mk.ukim.finki.akreditacii.service;


import mk.ukim.finki.akreditacii.model.Room;
import mk.ukim.finki.akreditacii.model.RoomType;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    Page<Room> findAllWithPagination(Integer pageNum, Integer pageSize);
    List<Room> findAll();
    Page<Room> findAllWithPaginationFiltered(Integer pageNum, Integer results, String nameSearch, String locationDescriptionSearch,String equipmentDescriptionSearch,Long participantsSearch,RoomType typeSearch);
    Room findByName(String name);
    Room create(String name, String locationDescription, String equipmentDescription, RoomType type, Long capacity);
    public Room update(String name, Room updatedRoom);
    Room delete(String name);
    List<Room> importData(List<Room> students);
}