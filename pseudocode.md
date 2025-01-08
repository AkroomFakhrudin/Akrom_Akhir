#PROGRAM Leasing

VAR
    noPelanggan, namaPelanggan, kodeMotor, hargaPokok, angsuran, totalBunga, jumlahDp: ARRAY
    isRunning: BOOLEAN = TRUE
    br: BufferedReader

FUNCTION main()
    PRINT header
    WHILE isRunning DO
        CALL showMenu()

FUNCTION showMenu()
    PRINT menu_options
    INPUT selectedMenu
    SWITCH selectedMenu
        CASE 1:
            CALL showAllPelanggan()
        CASE 2:
            CALL inputPelanggan()
        CASE 3:
            CALL editPelanggan()
        CASE 4:
            CALL deletePelanggan()
        CASE 5:
            isRunning = FALSE
            PRINT exit_message
        DEFAULT:
            PRINT invalid_menu_message

FUNCTION showAllPelanggan()
    IF noPelanggan IS EMPTY THEN
        PRINT "Data Customer Tidak Tersedia!"
        RETURN
    ENDIF
    DISPLAY customers_in_pages()
    REPEAT UNTIL user_exits()

FUNCTION inputPelanggan()
    DO
        PRINT input_prompts
        INPUT nomor, nama, kode, dp, angsur
        bunga = (angsur / 12) * 0.10
        ADD nomor, nama, kode, harga, angsur, dp, bunga TO respective_arrays
        PRINT "Data berhasil ditambahkan!"
        ASK "Mau nambah data lagi?"
    WHILE user_says_yes()

FUNCTION editPelanggan()
    PRINT "Edit Data"
    CALL showAllPelanggan()
    INPUT index
    IF index VALID THEN
        INPUT new_values
        UPDATE arrays[index] WITH new_values
        PRINT "Data berhasil diubah!"
    ELSE
        PRINT "Data tidak ditemukan!"
    ENDIF

FUNCTION deletePelanggan()
    PRINT "Hapus Data"
    CALL showAllPelanggan()
    INPUT index
    IF index VALID THEN
        REMOVE arrays[index]
        PRINT "Data berhasil dihapus!"
    ELSE
        PRINT "Data tidak ditemukan!"
    ENDIF

FUNCTION getHarga(kode)
    RETURN harga_based_on_kode

FUNCTION getMotor(kode)
    RETURN motor_type_based_on_kode
