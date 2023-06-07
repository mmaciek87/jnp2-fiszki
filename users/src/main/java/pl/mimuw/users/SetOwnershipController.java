package pl.mimuw.users;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SetOwnershipController {
    private final SetOwnershipRepository setOwnershipRepository;

    @PostMapping("/markUserAsOwner")
    public void markUserAsOwner(
            @RequestParam Long userId,
            @RequestParam Long setId
    ) {
        setOwnershipRepository.save(
            SetOwnershipDTO.builder()
                    .ownerId(userId)
                    .setId(setId)
                    .build()
        );
    }

    @GetMapping("/isOwner")
    public boolean isOwner(
            @RequestParam Long userId,
            @RequestParam Long setId
    ) {
        var set = setOwnershipRepository.findById(setId);
        return set.isPresent() && set.get().ownerId().equals(userId);
    }

    @DeleteMapping("/deleteSet")
    public void deleteSet(
            @RequestParam Long setId
    ) {
        setOwnershipRepository.deleteById(setId);
    }

    @GetMapping("/allSets")
    public List<Long> allUserSets(@RequestParam Long userId) {
        return setOwnershipRepository.findAllByOwnerId(userId).stream()
                .map(SetOwnershipDTO::setId)
                .toList();
    }
}
