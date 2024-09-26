package mk.ukim.finki.akreditacii.web;

import mk.ukim.finki.akreditacii.model.Room;
import mk.ukim.finki.akreditacii.model.RoomType;
import mk.ukim.finki.akreditacii.repository.RoomRepository;
import mk.ukim.finki.akreditacii.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    public RoomController(RoomService roomService, RoomRepository roomRepository) {
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public ResponseEntity<List<Room>> findAllRooms() {
        List<Room> rooms = roomService.findAll();
        return ResponseEntity.ok(rooms);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteRoomByName(@PathVariable String name) {
        try {
            roomService.delete(name);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting the room");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Room> getRoomByName(@PathVariable String name) {
        Room room = roomService.findByName(name);
        return ResponseEntity.ok(room);
    }


    @PostMapping("/edit/{name}")
    public ResponseEntity<?> updateRoom(
            @PathVariable String name,
            @RequestBody Room updatedRoom) {
        // Update room using updatedRoom object properties
        roomService.update(name, updatedRoom);
        return ResponseEntity.ok("Room updated successfully");
    }
    @GetMapping("/room-types")
    public RoomType[] getAllRoomTypes() {
        return RoomType.values();
    }

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
    }
}
