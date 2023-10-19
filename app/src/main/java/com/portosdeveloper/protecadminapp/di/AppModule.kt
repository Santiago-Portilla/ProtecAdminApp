package com.portosdeveloper.protecadminapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.portosdeveloper.protecadminapp.core.Constants.BUTTON
import com.portosdeveloper.protecadminapp.core.Constants.CLOTH
import com.portosdeveloper.protecadminapp.core.Constants.PACKAGE
import com.portosdeveloper.protecadminapp.core.Constants.PACKING
import com.portosdeveloper.protecadminapp.core.Constants.PLOTTER
import com.portosdeveloper.protecadminapp.core.Constants.REFLECTIVE
import com.portosdeveloper.protecadminapp.core.Constants.ROLLWADDING
import com.portosdeveloper.protecadminapp.core.Constants.SHIRT
import com.portosdeveloper.protecadminapp.core.Constants.THREAD
import com.portosdeveloper.protecadminapp.core.Constants.TOTALCLOTH
import com.portosdeveloper.protecadminapp.core.Constants.USERS
import com.portosdeveloper.protecadminapp.core.Constants.USERSWORKSHOP
import com.portosdeveloper.protecadminapp.core.Constants.UTILS
import com.portosdeveloper.protecadminapp.core.Constants.WADDING
import com.portosdeveloper.protecadminapp.core.Constants.YARN
import com.portosdeveloper.protecadminapp.data.repository.*
import com.portosdeveloper.protecadminapp.domain.repository.*
import com.portosdeveloper.protecadminapp.domain.use_cases._package.*
import com.portosdeveloper.protecadminapp.domain.use_cases.auth.*
import com.portosdeveloper.protecadminapp.domain.use_cases.button.StockButton
import com.portosdeveloper.protecadminapp.domain.use_cases.cloth.*
import com.portosdeveloper.protecadminapp.domain.use_cases.packing.StockPacking
import com.portosdeveloper.protecadminapp.domain.use_cases.plotter.*
import com.portosdeveloper.protecadminapp.domain.use_cases.reflective.StockReflective
import com.portosdeveloper.protecadminapp.domain.use_cases.roll_wadding.GetTotalMetersRollWadding
import com.portosdeveloper.protecadminapp.domain.use_cases.roll_wadding.StockRollWadding
import com.portosdeveloper.protecadminapp.domain.use_cases.shirt.CreateShirt
import com.portosdeveloper.protecadminapp.domain.use_cases.shirt.GetShirtById
import com.portosdeveloper.protecadminapp.domain.use_cases.shirt.ShirtUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.shirt.UpdateShirt
import com.portosdeveloper.protecadminapp.domain.use_cases.thread.StockThread
import com.portosdeveloper.protecadminapp.domain.use_cases.user.*
import com.portosdeveloper.protecadminapp.domain.use_cases.user_work_shop.*
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.GetList
import com.portosdeveloper.protecadminapp.domain.use_cases.utils.UtilsUseCases
import com.portosdeveloper.protecadminapp.domain.use_cases.wadding.*
import com.portosdeveloper.protecadminapp.domain.use_cases.yarn.StockYarn
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFireBaseFireStore():FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun providesUsersRef(db : FirebaseFirestore) : CollectionReference = db.collection(USERS)

    @Provides
    @Named(USERSWORKSHOP)
    fun providesUserWorkShopRef(db : FirebaseFirestore) : CollectionReference = db.collection(USERSWORKSHOP)

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage : FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(CLOTH)
    fun providesClothRef(db : FirebaseFirestore) : CollectionReference = db.collection(CLOTH)

    @Provides
    @Named(TOTALCLOTH)
    fun providesTotalClothRef(db : FirebaseFirestore) : CollectionReference = db.collection(TOTALCLOTH)

    @Provides
    @Named(PLOTTER)
    fun providesPlotterRef(db : FirebaseFirestore) : CollectionReference = db.collection(PLOTTER)

    @Provides
    @Named(WADDING)
    fun providesWaddingRef(db : FirebaseFirestore) : CollectionReference = db.collection(WADDING)

    @Provides
    @Named(ROLLWADDING)
    fun providesRollWaddingRef(db : FirebaseFirestore) : CollectionReference = db.collection(ROLLWADDING)

    @Provides
    @Named(THREAD)
    fun providesThreadRef(db : FirebaseFirestore) : CollectionReference = db.collection(THREAD)

    @Provides
    @Named(YARN)
    fun providesYarnRef(db : FirebaseFirestore) : CollectionReference = db.collection(YARN)

    @Provides
    @Named(REFLECTIVE)
    fun providesReflectiveRef(db : FirebaseFirestore) : CollectionReference = db.collection(REFLECTIVE)

    @Provides
    @Named(BUTTON)
    fun providesButtonRef(db : FirebaseFirestore) : CollectionReference = db.collection(BUTTON)

    @Provides
    @Named(PACKING)
    fun providesPackingRef(db : FirebaseFirestore) : CollectionReference = db.collection(PACKING)

    @Provides
    @Named(UTILS)
    fun providesUtilsRef(db : FirebaseFirestore) : CollectionReference = db.collection(UTILS)

    @Provides
    @Named(PACKAGE)
    fun providesPackageRef(db : FirebaseFirestore) : CollectionReference = db.collection(PACKAGE)

    @Provides
    @Named(SHIRT)
    fun providesShirtRef(db : FirebaseFirestore) : CollectionReference = db.collection(SHIRT)

    @Provides
    fun provideAuthRepository(Impl : AuthRepositoryImpl) : AuthRepository = Impl

    @Provides
    fun providesUsersRepository(Impl : UsersRepositoryImpl) : UsersRepository = Impl

    @Provides
    fun providesUsersWorkShopRepository(Impl : UserWorkShopReposiotryImpl) : UserWorkShopRepository = Impl

    @Provides
    fun providesClothRepository(Impl : ClothRepositoryImpl) : ClothRepository = Impl

    @Provides
    fun providesPlotterRepository(Impl : PlotterRepositoryImpl) : PlotterRepository = Impl

    @Provides
    fun providesWaddingRepository(Impl : WaddingRepositoryImpl) : WaddingRepository = Impl

    @Provides
    fun providesRollWaddingRepository(Impl : RollWaddingRepositoryImpl) : RollWaddingRepository = Impl

    @Provides
    fun providesThreadRepository(Impl : ThreadRepositoryImpl) : ThreadRepository = Impl

    @Provides
    fun providesYarnRepository(Impl : YarnRepositoryImpl) : YarnRepository = Impl

    @Provides
    fun providesReflectiveRepository(Impl : ReflectiveRepositoryImpl) : ReflectiveRepository = Impl

    @Provides
    fun providesButtonRepository(Impl : ButtonRepositoryImpl) : ButtonRepository = Impl

    @Provides
    fun providesPackingRepository(Impl : PackingRepositoryImpl) : PackingRepository = Impl

    @Provides
    fun providesUtilsRepository(Impl : UtilsRepositoryImpl) : UtilsRepository = Impl

    @Provides
    fun providesPackageRepository(Impl : PackageRepositoryImpl) : PackageRepository = Impl

    @Provides
    fun providesShirtRepository(Impl: ShirtRepositoryImpl) : ShirtRepository = Impl


    @Provides
    fun provideAuthUseCases(repository : AuthRepository) = AuthUseCases(
        login = Login(repository),
        getCurrentUser = GetCurrentUser(repository),
        signUp = SignUp(repository),
        logOut = LogOut(repository)
    )

    @Provides
    fun provideUserUseCases(repository : UsersRepository) = UserUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun provideUserWorkShopUseCases(repository : UserWorkShopRepository) = UserWorkShopUseCases(
        getCutUserWorkShop = GetCutUserWorkShop(repository),
        getUserWorkShopList = GetUserWorkShopList(repository),
        updateWorkFinishList = UpdateWorkFinishList(repository),
        updateWorkPaidList = UpdateWorkPaidList(repository),
        updateWorkProgressList = UpdateWorkProgressList(repository),
        updateSalaryWorkMonth = UpdateSalaryWorkMonth(repository),
        updateSalaryWorkWeek = UpdateSalaryWorkWeek(repository)
    )


    @Provides
    fun provideClothUseCases(repository : ClothRepository) = ClothUseCases(
        createCloth = CreateCloth(repository),
        createTotalCloth = CreateTotalCloth(repository),
        getTotalMeasureById = GetTotalMeasureById(repository),
        updateTotalCloth = UpdateTotalCloth(repository),
        stockTotalCloth = StockTotalCloth(repository),
        getClothList = GetClothList(repository),
        updateCloth = UpdateCloth(repository),
        addDateTotalCloth = AddDateTotalCloth(repository)
    )

    @Provides
    fun providePlotterUseCases(repository : PlotterRepository) = PlotterUseCases(
        createPlotter = CreatePlotter(repository),
        getIdPlotter = GetIdPlotter(repository),
        stockPlotter = StockPlotter(repository),
        getPlotterList = GetPlotterList(repository),
        updatePlotter = UpdatePlotter(repository)
    )

    @Provides
    fun provideWaddingUseCases(repository : WaddingRepository) = WaddingUseCases(
        createWadding = CreateWadding(repository),
        updateWadding = UpdateWadding(repository),
        getTotalWadding = GetTotalWadding(repository),
        addDateWadding = AddDateWadding(repository),
        stockWadding = StockWadding(repository)
    )

    @Provides
    fun provideRollWaddingUseCases(repository : RollWaddingRepository) = RollWaddingUseCases(
        createRollWadding = CreateRollWadding(repository),
        updateRollWadding = UpdateRollWadding(repository),
        getTotalRollWadding = GetTotalRollWadding(repository),
        addDateRollWadding = AddDateRollWadding(repository),
        stockRollWadding = StockRollWadding(repository),
        getTotalMetersRollWadding = GetTotalMetersRollWadding(repository)
    )

    @Provides
    fun provideThreadUseCases(repository : ThreadRepository) = ThreadUseCases(
        createThread = CreateThread(repository),
        updateThread = UpdateThread(repository),
        getTotalThread = GetTotalThread(repository),
        addTotalThreadDay = AddTotalThreadDay(repository),
        stockThread = StockThread(repository)
    )

    @Provides
    fun provideYarnUseCases(repository : YarnRepository) = YarnUseCases(
        createYarn = CreateYarn(repository),
        updateYarn = UpdateYarn(repository),
        getTotalYarn = GetTotalYarn(repository),
        addTotalYarnDay = AddTotalYarnDay(repository),
        stockYarn = StockYarn(repository)
    )

    @Provides
    fun provideReflectiveUseCases(repository : ReflectiveRepository) = ReflectiveUseCases(
        createReflective = CreateReflective(repository),
        updateReflective = UpdateReflective(repository),
        getTotalReflective = GetTotalReflective(repository),
        addDateReflective = AddDateReflective(repository),
        addTotalReflectiveDay = AddTotalReflectiveDay(repository),
        stockReflective = StockReflective(repository)
    )

    @Provides
    fun provideButtonUseCases(repository : ButtonRepository) = ButtonUseCases(
        createButton = CreateButton(repository),
        updateButton = UpdateButton(repository),
        getTotalButton = GetTotalButton(repository),
        addTotalButtonDay = AddTotalButtonDay(repository),
        stockButton = StockButton(repository)
    )

    @Provides
    fun providePackingUseCases(repository : PackingRepository) = PackingUseCases(
        createPacking = CreatePacking(repository),
        updatePacking = UpdatePacking(repository),
        getTotalPacking = GetTotalPacking(repository),
        addDatePacking = AddDatePacking(repository),
        addTotalPackingDay = AddTotalPackingDay(repository),
        stockPacking = StockPacking(repository)
    )

    @Provides
    fun provideUtilsUseCases(repository : UtilsRepository) = UtilsUseCases(
        getList = GetList(repository)
    )

    @Provides
    fun providePackageUseCases(repository : PackageRepository) = PackageUseCases(
        getIdPackage = GetIdPackage(repository),
        createPackage = CreatePackage(repository),
        stockPackage = StockPackage(repository),
        updatePaidJob = UpdatePaidJob(repository),
        newGetPackageById = NewGetPackageById(repository)
    )

    @Provides
    fun provideShirtUseCases(repository: ShirtRepository) = ShirtUseCases(
        createShirt = CreateShirt(repository),
        updateShirt = UpdateShirt(repository),
        getShirtById = GetShirtById(repository)
    )
}