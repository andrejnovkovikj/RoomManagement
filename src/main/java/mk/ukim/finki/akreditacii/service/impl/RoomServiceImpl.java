package mk.ukim.finki.akreditacii.service.impl;

import mk.ukim.finki.akreditacii.model.Room;
import mk.ukim.finki.akreditacii.model.RoomType;
import mk.ukim.finki.akreditacii.model.exceptions.InvalidRoomIdException;
import mk.ukim.finki.akreditacii.repository.RoomRepository;
import mk.ukim.finki.akreditacii.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Page<Room> findAllWithPagination(Integer pageNum, Integer results) {

        PageRequest pageRequest = PageRequest.of(pageNum - 1, results);
        return roomRepository.findAll(pageRequest);
    }
    @Override
    public Page<Room> findAllWithPaginationFiltered(Integer pageNum, Integer results,
                                                    String nameSearch,
                                                    String locationDescriptionSearch,
                                                    String equipmentDescriptionSearch,
                                                    Long participantsSearch,
                                                    RoomType typeSearch) {

        PageRequest pageRequest = PageRequest.of(pageNum - 1, results);


        return roomRepository.findAllFiltered(nameSearch,locationDescriptionSearch,equipmentDescriptionSearch,participantsSearch,typeSearch,pageRequest);

    }

    @Override
    public Room findByName(String name) {

        return this.roomRepository.findByName(name);
    }
    @Override
    public Room create( String name, String locationDescription, String equipmentDescription, RoomType type, Long capacity) {

        Room room = new Room( name, locationDescription,equipmentDescription,type,capacity);
        return this.roomRepository.save(room);
    }

    @Override
    public Room update(String name, Room updatedRoom) {
        Room roomToUpdate = roomRepository.findByName(name);

        roomToUpdate.setLocationDescription(updatedRoom.getLocationDescription());
        roomToUpdate.setEquipmentDescription(updatedRoom.getEquipmentDescription());
        roomToUpdate.setType(updatedRoom.getType());
        roomToUpdate.setCapacity(updatedRoom.getCapacity());

        return roomRepository.save(roomToUpdate);
    }

    @Override
    public Room delete(String name) {

        Room room = findByName(name);
        this.roomRepository.delete(room);
        return room;
    }

    @Override
    public List<Room> importData(List<Room> data) {
        return null;
    }

}
