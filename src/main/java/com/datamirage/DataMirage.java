package com.datamirage;

import com.datamirage.locale.DataMirageLocale;
import com.datamirage.providers.*;
import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;

/**
 * DataMirage is a comprehensive fake data generation library that provides various providers
 * for generating realistic fake data in different categories.
 * 
 * <p>This class serves as the main entry point for accessing all fake data providers.
 * Each provider is responsible for generating specific types of fake data.</p>
 * 
 * <p>Example usage:
 * <pre>
 * {@code
 * DataMirage dataMirage = new DataMirage();
 * String name = dataMirage.name().firstName();
 * String address = dataMirage.address().streetAddress();
 * }
 * </pre>
 * </p>
 */
public class DataMirage {
    private final RandomService random;
    private final AddressProvider addressProvider;
    private final NameProvider nameProvider;
    private final CompanyProvider companyProvider;
    private final InternetProvider internetProvider;
    private final BookProvider bookProvider;
    private final ColorProvider colorProvider;
    private final FoodProvider foodProvider;
    private final MusicProvider musicProvider;
    private final PhoneNumberProvider phoneNumberProvider;
    private final WeatherProvider weatherProvider;
    private final FilmProvider filmProvider;
    private final AnimalProvider animalProvider;
    private final VehicleProvider vehicleProvider;
    private final ScienceProvider scienceProvider;
    private final SystemProvider systemProvider;
    private final GitProvider gitProvider;
    private final CryptoProvider cryptoProvider;
    private final CommerceProvider commerceProvider;
    private final CodeProvider codeProvider;
    private final DateProvider dateProvider;
    private final FinanceProvider financeProvider;
    private final ArtistProvider artistProvider;
    private final AppProvider appProvider;
    private final HelpersProvider helpersProvider;
    private final BoolProvider boolProvider;
    private final ImageProvider imageProvider;
    private final NumberProvider numberProvider;
    private final StringProvider stringProvider;
    private final WordProvider wordProvider;

    /**
     * Constructs a new DataMirage instance with the default locale (Turkish).
     */
    public DataMirage() {
        this(DataMirageLocale.TR_TR);
    }

    /**
     * Constructs a new DataMirage instance with the specified locale.
     *
     * @param locale The locale to use for generating localized fake data
     */
    public DataMirage(DataMirageLocale locale) {
        this.random = new RandomService();
        this.addressProvider = new AddressProvider(random);
        this.nameProvider = new NameProvider(random);
        this.companyProvider = new CompanyProvider(random);
        this.internetProvider = new InternetProvider(random);
        this.bookProvider = new BookProvider(random);
        this.colorProvider = new ColorProvider(random);
        this.foodProvider = new FoodProvider(random);
        this.musicProvider = new MusicProvider(random);
        this.phoneNumberProvider = new PhoneNumberProvider(random);
        this.weatherProvider = new WeatherProvider(random);
        this.filmProvider = new FilmProvider(random);
        this.animalProvider = new AnimalProvider(random);
        this.vehicleProvider = new VehicleProvider(random);
        this.scienceProvider = new ScienceProvider(random);
        this.systemProvider = new SystemProvider(random);
        this.gitProvider = new GitProvider(random);
        this.cryptoProvider = new CryptoProvider(random);
        this.commerceProvider = new CommerceProvider(random);
        this.codeProvider = new CodeProvider(random);
        this.dateProvider = new DateProvider(random);
        this.financeProvider = new FinanceProvider(random);
        this.artistProvider = new ArtistProvider(random);
        this.appProvider = new AppProvider(random);
        this.helpersProvider = new HelpersProvider(random);
        this.boolProvider = new BoolProvider(random);
        this.imageProvider = new ImageProvider();
        this.numberProvider = new NumberProvider(random);
        this.stringProvider = new StringProvider(random);
        this.wordProvider = new WordProvider(random);

        DataLoader.setLocale(locale);
    }

    /**
     * Returns the address provider for generating fake address data.
     *
     * @return The address provider instance
     */
    public AddressProvider address() {
        return addressProvider;
    }

    /**
     * Returns the name provider for generating fake name data.
     *
     * @return The name provider instance
     */
    public NameProvider name() {
        return nameProvider;
    }

    /**
     * Returns the company provider for generating fake company data.
     *
     * @return The company provider instance
     */
    public CompanyProvider company() {
        return companyProvider;
    }

    /**
     * Returns the internet provider for generating fake internet-related data.
     *
     * @return The internet provider instance
     */
    public InternetProvider internet() {
        return internetProvider;
    }

    /**
     * Returns the book provider for generating fake book data.
     *
     * @return The book provider instance
     */
    public BookProvider book() {
        return bookProvider;
    }

    /**
     * Returns the color provider for generating fake color data.
     *
     * @return The color provider instance
     */
    public ColorProvider color() {
        return colorProvider;
    }

    /**
     * Returns the food provider for generating fake food data.
     *
     * @return The food provider instance
     */
    public FoodProvider food() {
        return foodProvider;
    }

    /**
     * Returns the music provider for generating fake music data.
     *
     * @return The music provider instance
     */
    public MusicProvider music() {
        return musicProvider;
    }

    /**
     * Returns the phone number provider for generating fake phone number data.
     *
     * @return The phone number provider instance
     */
    public PhoneNumberProvider phoneNumber() {
        return phoneNumberProvider;
    }

    /**
     * Returns the weather provider for generating fake weather data.
     *
     * @return The weather provider instance
     */
    public WeatherProvider weather() {
        return weatherProvider;
    }

    /**
     * Returns the film provider for generating fake film data.
     *
     * @return The film provider instance
     */
    public FilmProvider film() {
        return filmProvider;
    }

    /**
     * Returns the animal provider for generating fake animal data.
     *
     * @return The animal provider instance
     */
    public AnimalProvider animal() {
        return animalProvider;
    }

    /**
     * Returns the vehicle provider for generating fake vehicle data.
     *
     * @return The vehicle provider instance
     */
    public VehicleProvider vehicle() {
        return vehicleProvider;
    }

    /**
     * Returns the science provider for generating fake science data.
     *
     * @return The science provider instance
     */
    public ScienceProvider science() {
        return scienceProvider;
    }

    /**
     * Returns the system provider for generating fake system data.
     *
     * @return The system provider instance
     */
    public SystemProvider system() {
        return systemProvider;
    }

    /**
     * Returns the git provider for generating fake git-related data.
     *
     * @return The git provider instance
     */
    public GitProvider git() {
        return gitProvider;
    }

    /**
     * Returns the crypto provider for generating fake cryptocurrency data.
     *
     * @return The crypto provider instance
     */
    public CryptoProvider crypto() {
        return cryptoProvider;
    }

    /**
     * Returns the commerce provider for generating fake commerce data.
     *
     * @return The commerce provider instance
     */
    public CommerceProvider commerce() {
        return commerceProvider;
    }

    /**
     * Returns the code provider for generating fake code data.
     *
     * @return The code provider instance
     */
    public CodeProvider code() {
        return codeProvider;
    }

    /**
     * Returns the date provider for generating fake date data.
     *
     * @return The date provider instance
     */
    public DateProvider date() {
        return dateProvider;
    }

    /**
     * Returns the finance provider for generating fake finance data.
     *
     * @return The finance provider instance
     */
    public FinanceProvider finance() {
        return financeProvider;
    }

    /**
     * Returns the artist provider for generating fake artist data.
     *
     * @return The artist provider instance
     */
    public ArtistProvider artist() {
        return artistProvider;
    }

    /**
     * Returns the app provider for generating fake app data.
     *
     * @return The app provider instance
     */
    public AppProvider app() {
        return appProvider;
    }

    /**
     * Returns the helpers provider for generating various utility data.
     *
     * @return The helpers provider instance
     */
    public HelpersProvider helpers() {
        return helpersProvider;
    }

    /**
     * Returns the bool provider for generating fake boolean data.
     *
     * @return The bool provider instance
     */
    public BoolProvider bool() {
        return boolProvider;
    }

    /**
     * Returns the image provider for generating fake image data.
     *
     * @return The image provider instance
     */
    public ImageProvider image() {
        return imageProvider;
    }

    /**
     * Returns the number provider for generating fake numeric data.
     *
     * @return The number provider instance
     */
    public NumberProvider number() {
        return numberProvider;
    }

    /**
     * Returns the string provider for generating fake string data.
     *
     * @return The string provider instance
     */
    public StringProvider string() {
        return stringProvider;
    }

    /**
     * Returns the word provider for generating fake word data.
     *
     * @return The word provider instance
     */
    public WordProvider word() {
        return wordProvider;
    }
} 