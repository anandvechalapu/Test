·       If user has access then user able to access this page.
·       If user didn’t have access then redirect to login page.
·       Validate the given Input data based on the validation rules.
·       If the validations are failed display respectives validation errors .
·       If no validation errors then update Global Settings/Setup Values.
·       The Submit and Reset buttons are given on this screen.

Controller:

@RestController
@RequestMapping("/global-settings")
public class GlobalSettingsController {
 
    @Autowired
    private GlobalSettingsService globalSettingsService;
 
    @PostMapping
    public ResponseEntity<GlobalSettings> updateGlobalSettings(@RequestBody GlobalSettings globalSettings) {
        GlobalSettings updatedSettings = globalSettingsService.updateGlobalSettings(globalSettings);
        return ResponseEntity.ok(updatedSettings);
    }
 
    @GetMapping
    public ResponseEntity<GlobalSettings> getGlobalSettings(@RequestParam Long dbId) {
        if (dbId == null) {
            return ResponseEntity.badRequest().build();
        }
        GlobalSettings globalSettings = globalSettingsService.getGlobalSettings(dbId);
        return ResponseEntity.ok(globalSettings);
    }
}

Service:

@Service
public class GlobalSettingsService {
 
    @Autowired
    private GlobalSettingsRepository globalSettingsRepository;
 
    public GlobalSettings updateGlobalSettings(GlobalSettings globalSettings) {
        validateGlobalSettingsInput(globalSettings);
        return globalSettingsRepository.save(globalSettings);
    }
 
    public GlobalSettings getGlobalSettings(Long dbId) {
        return globalSettingsRepository.findById(dbId).orElseThrow(() -> new ResourceNotFoundException("No settings found for dbId: " + dbId));
    }
 
    private void validateGlobalSettingsInput(GlobalSettings globalSettings) {
        if (globalSettings.getWeightingFactor0Mins() == null || globalSettings.getWeightingFactor0Mins() < 0 || globalSettings.getWeightingFactor0Mins() > 100) {
            throw new ValidationException("SETUP INVALID PERCENTAGE VALUE");
        }
        if (globalSettings.getWeightingFactor110Mins() == null || globalSettings.getWeightingFactor110Mins() < 0 || globalSettings.getWeightingFactor110Mins() > 100) {
            throw new ValidationException("SETUP INVALID PERCENTAGE VALUE");
        }
        if (globalSettings.getWeightingFactor1120Mins() == null || globalSettings.getWeightingFactor1120Mins() < 0 || globalSettings.getWeightingFactor1120Mins() > 100) {
            throw new ValidationException("SETUP INVALID PERCENTAGE VALUE");
        }
        if (globalSettings.getWeightingFactor2140Mins() == null || globalSettings.getWeightingFactor2140Mins() < 0 || globalSettings.getWeightingFactor2140Mins() > 100) {
            throw new ValidationException("SETUP INVALID PERCENTAGE VALUE");
        }
        if (globalSettings.getWeightingFactor3160Mins() == null || globalSettings.getWeightingFactor3160Mins() < 0 || globalSettings.getWeightingFactor3160Mins() > 100) {
            throw new ValidationException("SETUP INVALID PERCENTAGE VALUE");
        }
        if (globalSettings.getWeightingFactor60PlusMins() == null || globalSettings.getWeightingFactor60PlusMins() < 0 || globalSettings.get