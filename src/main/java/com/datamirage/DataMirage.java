package com.datamirage;

import com.datamirage.locale.DataMirageLocale;
import com.datamirage.providers.*;
import com.datamirage.util.BatchGenerator;
import com.datamirage.util.DataContext;
import com.datamirage.util.DataExporter;
import com.datamirage.util.DataLoader;
import com.datamirage.util.RandomService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DataMirage is a comprehensive fake data generation library that provides various providers
 * for generating realistic fake data in different categories.
 *
 * <p>This class serves as the main entry point for accessing all fake data providers.
 * Each provider is responsible for generating specific types of fake data.</p>
 *
 * <p>Providers are lazily initialized on first access for better startup performance.</p>
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
    private final DataMirageLocale locale;
    private final DataContext context;
    private final RandomService random;

    // Lazy-loaded provider cache
    private final Map<Class<?>, Object> providers = new ConcurrentHashMap<>();

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
        this(locale, null);
    }

    /**
     * Constructs a new DataMirage instance with the specified locale and seed.
     * When a seed is provided, the random number generator will produce reproducible results.
     *
     * @param locale The locale to use for generating localized fake data
     * @param seed The seed for the random number generator (null for non-deterministic)
     */
    @SuppressWarnings("deprecation")
    public DataMirage(DataMirageLocale locale, Long seed) {
        this.locale = locale;
        this.context = new DataContext(locale);
        this.random = seed != null ? new RandomService(seed) : new RandomService();

        // Maintain backward compatibility for code using DataLoader directly
        DataLoader.setLocale(locale);
    }

    /**
     * Gets or creates a provider instance lazily.
     *
     * @param <T> The provider type
     * @param providerClass The class of the provider
     * @param factory A factory function to create the provider
     * @return The provider instance
     */
    @SuppressWarnings("unchecked")
    private <T> T getOrCreateProvider(Class<T> providerClass, java.util.function.Supplier<T> factory) {
        return (T) providers.computeIfAbsent(providerClass, k -> factory.get());
    }

    /**
     * Returns the locale associated with this DataMirage instance.
     *
     * @return The locale
     */
    public DataMirageLocale getLocale() {
        return locale;
    }

    /**
     * Returns the address provider for generating fake address data.
     *
     * @return The address provider instance
     */
    public AddressProvider address() {
        return getOrCreateProvider(AddressProvider.class, () -> new AddressProvider(random, context));
    }

    /**
     * Returns the name provider for generating fake name data.
     *
     * @return The name provider instance
     */
    public NameProvider name() {
        return getOrCreateProvider(NameProvider.class, () -> new NameProvider(random, context));
    }

    /**
     * Returns the company provider for generating fake company data.
     *
     * @return The company provider instance
     */
    public CompanyProvider company() {
        return getOrCreateProvider(CompanyProvider.class, () -> new CompanyProvider(random, context));
    }

    /**
     * Returns the internet provider for generating fake internet-related data.
     *
     * @return The internet provider instance
     */
    public InternetProvider internet() {
        return getOrCreateProvider(InternetProvider.class, () -> new InternetProvider(random, context));
    }

    /**
     * Returns the book provider for generating fake book data.
     *
     * @return The book provider instance
     */
    public BookProvider book() {
        return getOrCreateProvider(BookProvider.class, () -> new BookProvider(random, context));
    }

    /**
     * Returns the color provider for generating fake color data.
     *
     * @return The color provider instance
     */
    public ColorProvider color() {
        return getOrCreateProvider(ColorProvider.class, () -> new ColorProvider(random, context));
    }

    /**
     * Returns the food provider for generating fake food data.
     *
     * @return The food provider instance
     */
    public FoodProvider food() {
        return getOrCreateProvider(FoodProvider.class, () -> new FoodProvider(random, context));
    }

    /**
     * Returns the music provider for generating fake music data.
     *
     * @return The music provider instance
     */
    public MusicProvider music() {
        return getOrCreateProvider(MusicProvider.class, () -> new MusicProvider(random, context));
    }

    /**
     * Returns the phone number provider for generating fake phone number data.
     *
     * @return The phone number provider instance
     */
    public PhoneNumberProvider phoneNumber() {
        return getOrCreateProvider(PhoneNumberProvider.class, () -> new PhoneNumberProvider(random, context));
    }

    /**
     * Returns the weather provider for generating fake weather data.
     *
     * @return The weather provider instance
     */
    public WeatherProvider weather() {
        return getOrCreateProvider(WeatherProvider.class, () -> new WeatherProvider(random, context));
    }

    /**
     * Returns the film provider for generating fake film data.
     *
     * @return The film provider instance
     */
    public FilmProvider film() {
        return getOrCreateProvider(FilmProvider.class, () -> new FilmProvider(random, context));
    }

    /**
     * Returns the animal provider for generating fake animal data.
     *
     * @return The animal provider instance
     */
    public AnimalProvider animal() {
        return getOrCreateProvider(AnimalProvider.class, () -> new AnimalProvider(random, context));
    }

    /**
     * Returns the vehicle provider for generating fake vehicle data.
     *
     * @return The vehicle provider instance
     */
    public VehicleProvider vehicle() {
        return getOrCreateProvider(VehicleProvider.class, () -> new VehicleProvider(random, context));
    }

    /**
     * Returns the science provider for generating fake science data.
     *
     * @return The science provider instance
     */
    public ScienceProvider science() {
        return getOrCreateProvider(ScienceProvider.class, () -> new ScienceProvider(random, context));
    }

    /**
     * Returns the system provider for generating fake system data.
     *
     * @return The system provider instance
     */
    public SystemProvider system() {
        return getOrCreateProvider(SystemProvider.class, () -> new SystemProvider(random, context));
    }

    /**
     * Returns the git provider for generating fake git-related data.
     *
     * @return The git provider instance
     */
    public GitProvider git() {
        return getOrCreateProvider(GitProvider.class, () -> new GitProvider(random, context));
    }

    /**
     * Returns the crypto provider for generating fake cryptocurrency data.
     *
     * @return The crypto provider instance
     */
    public CryptoProvider crypto() {
        return getOrCreateProvider(CryptoProvider.class, () -> new CryptoProvider(random, context));
    }

    /**
     * Returns the commerce provider for generating fake commerce data.
     *
     * @return The commerce provider instance
     */
    public CommerceProvider commerce() {
        return getOrCreateProvider(CommerceProvider.class, () -> new CommerceProvider(random, context));
    }

    /**
     * Returns the code provider for generating fake code data.
     *
     * @return The code provider instance
     */
    public CodeProvider code() {
        return getOrCreateProvider(CodeProvider.class, () -> new CodeProvider(random, context));
    }

    /**
     * Returns the date provider for generating fake date data.
     *
     * @return The date provider instance
     */
    public DateProvider date() {
        return getOrCreateProvider(DateProvider.class, () -> new DateProvider(random));
    }

    /**
     * Returns the finance provider for generating fake finance data.
     *
     * @return The finance provider instance
     */
    public FinanceProvider finance() {
        return getOrCreateProvider(FinanceProvider.class, () -> new FinanceProvider(random, context));
    }

    /**
     * Returns the artist provider for generating fake artist data.
     *
     * @return The artist provider instance
     */
    public ArtistProvider artist() {
        return getOrCreateProvider(ArtistProvider.class, () -> new ArtistProvider(random, context));
    }

    /**
     * Returns the app provider for generating fake app data.
     *
     * @return The app provider instance
     */
    public AppProvider app() {
        return getOrCreateProvider(AppProvider.class, () -> new AppProvider(random, context));
    }

    /**
     * Returns the helpers provider for generating various utility data.
     *
     * @return The helpers provider instance
     */
    public HelpersProvider helpers() {
        return getOrCreateProvider(HelpersProvider.class, () -> new HelpersProvider(random));
    }

    /**
     * Returns the bool provider for generating fake boolean data.
     *
     * @return The bool provider instance
     */
    public BoolProvider bool() {
        return getOrCreateProvider(BoolProvider.class, () -> new BoolProvider(random));
    }

    /**
     * Returns the image provider for generating fake image data.
     *
     * @return The image provider instance
     */
    public ImageProvider image() {
        return getOrCreateProvider(ImageProvider.class, ImageProvider::new);
    }

    /**
     * Returns the number provider for generating fake numeric data.
     *
     * @return The number provider instance
     */
    public NumberProvider number() {
        return getOrCreateProvider(NumberProvider.class, () -> new NumberProvider(random));
    }

    /**
     * Returns the string provider for generating fake string data.
     *
     * @return The string provider instance
     */
    public StringProvider string() {
        return getOrCreateProvider(StringProvider.class, () -> new StringProvider(random));
    }

    /**
     * Returns the word provider for generating fake word data.
     *
     * @return The word provider instance
     */
    public WordProvider word() {
        return getOrCreateProvider(WordProvider.class, () -> new WordProvider(random, context));
    }

    /**
     * Creates a new DataMirageBuilder for fluent configuration.
     *
     * @return A new DataMirageBuilder instance
     */
    public static DataMirageBuilder builder() {
        return new DataMirageBuilder();
    }

    /**
     * Returns a new DataExporter for exporting generated data to JSON format.
     *
     * @return A new DataExporter instance
     */
    public DataExporter exporter() {
        return new DataExporter();
    }

    /**
     * Returns a new BatchGenerator for generating batches of fake data.
     *
     * @return A new BatchGenerator instance
     */
    public BatchGenerator batch() {
        return new BatchGenerator();
    }
} 