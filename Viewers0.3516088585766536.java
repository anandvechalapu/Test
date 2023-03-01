
@SpringBootApplication
public class ViewersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewersApplication.class, args);
    }
}

@RestController
public class ViewersController {

    private final ViewersService viewersService;

    @GetMapping("/viewers")
    public List<Viewers> getViewers() {
        return viewersService.getViewers();
    }

    @GetMapping("/viewers/invoices")
    public List<Invoice> getInvoices() {
        return viewersService.getInvoices();
    }

    @GetMapping("/viewers/deliveryNotes")
    public List<DeliveryNote> getDeliveryNotes() {
        return viewersService.getDeliveryNotes();
    }

    @GetMapping("/viewers/creditNotes")
    public List<CreditNote> getCreditNotes() {
        return viewersService.getCreditNotes();
    }

    @GetMapping("/viewers/creditProcessed")
    public List<CreditProcessed> getCreditProcessed() {
        return viewersService.getCreditProcessed();
    }

    @GetMapping("/viewers/goodsSupplied")
    public List<GoodsSupplied> getGoodsSupplied() {
        return viewersService.getGoodsSupplied();
    }

    @GetMapping("/viewers/newspaperRecall")
    public List<NewspaperRecall> getNewspaperRecall() {
        return viewersService.getNewspaperRecall();
    }

    @GetMapping("/viewers/magazineRecall")
    public List<MagazineRecall> getMagazineRecall() {
        return viewersService.getMagazineRecall();
    }

    @GetMapping("/viewers/salePoint")
    public List<SalePoint> getSalePoint() {
        return viewersService.getSalePoint();
    }

    @GetMapping("/viewers/inmRebateCredit")
    public List<INMRebateCredit> getINMRebateCredit() {
        return viewersService.getINMRebateCredit();
    }

    @PostMapping("/viewers/search")
    public List<Viewers> searchViewers(@RequestBody SearchParams searchParams) {
        return viewersService.searchViewers(searchParams);
    }

    @PostMapping("/viewers/filter")
    public List<Viewers> filterViewers(@RequestBody FilterParams filterParams) {
        return viewersService.filterViewers(filterParams);
    }

    @PostMapping("/viewers/download")
    public List<Viewers> downloadViewers(@RequestBody DownloadParams downloadParams) {
        return viewersService.downloadViewers(downloadParams);
    }

}

@Service
public class ViewersService {

    private final ViewersRepository viewersRepository;

    public List<Viewers> getViewers() {
        return viewersRepository.findAll();
    }

    public List<Invoice> getInvoices() {
        return viewersRepository.findAllInvoices();
    }

    public List<DeliveryNote> getDeliveryNotes() {
        return viewersRepository.findAllDeliveryNotes();
    }

    public List<CreditNote> getCreditNotes() {
        return viewersRepository.findAllCreditNotes();
    }

    public List<CreditProcessed> getCreditProcessed() {
        return viewersRepository.findAllCreditProcess