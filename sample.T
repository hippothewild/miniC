    AREA SP
    AREA FP
    AREA VR
    AREA MEM
LAB START
    MOVE END MEM(0)
    MOVE 0 MEM(1)
    MOVE 1 SP
    MOVE 1 FP
    ADD SP@ 1 SP
    MOVE 0 MEM(SP@)
    ADD SP@ 1 SP
    MOVE END MEM(SP@)
    ADD SP@ 1 SP
    MOVE 0 MEM(SP@)
    MOVE SP@ FP
    JMP main
LAB f1
    MOVE 1.1 VR(0)
    MOVE VR(0)@ VR(1)
    ADD FP@ -2 VR(0)
    MOVE MEM(VR(0)@)@ VR(0)
    FMUL VR(1)@ VR(0)@ VR(0)
    MOVE VR(0)@ VR(1)
    ADD 1 1 VR(0)
    MOVE VR(1)@ MEM(VR(0)@)
    ADD 1 1 VR(0)
    MOVE MEM(VR(0)@)@ VR(0)
    MOVE VR(0)@ VR(1)
    MOVE 1.2 VR(0)
    FSUB VR(1)@ VR(0)@ VR(0)
    MOVE FP@ SP
    MOVE MEM(FP@)@ FP
    SUB SP@ 1 SP
    JMP MEM(SP@)@
    MOVE FP@ SP
    MOVE MEM(FP@)@ FP
    SUB SP@ 1 SP
    JMP MEM(SP@)@
LAB main
    ADD SP@ 1 SP
    MOVE 0 MEM(SP@)
    ADD SP@ 1 SP
    MOVE 0 MEM(SP@)
    MOVE 2.3 VR(0)
    MOVE VR(0)@ VR(1)
    MOVE 12 VR(0)
    I2F VR(0)@ VR(0)
    FMUL VR(1)@ VR(0)@ VR(0)
    MOVE VR(0)@ VR(1)
    ADD FP@ 1 VR(0)
    MOVE VR(1)@ MEM(VR(0)@)
    ADD FP@ 1 VR(0)
    MOVE MEM(VR(0)@)@ VR(0)
    ADD SP@ 1 SP
    MOVE VR(0)@ MEM(SP@)
    ADD SP@ 1 SP
    MOVE LABEL1 MEM(SP@)
    ADD SP@ 1 SP
    MOVE FP@ MEM(SP@)
    MOVE SP@ FP
    JMP   f1
LAB LABEL1
    SUB SP@ 2 SP
    MOVE VR(0)@ VR(1)
    ADD FP@ 2 VR(0)
    MOVE VR(1)@ MEM(VR(0)@)
    ADD FP@ 2 VR(0)
    MOVE MEM(VR(0)@)@ VR(0)
    WRITE VR(0)@
    MOVE 0 VR(0)
    MOVE FP@ SP
    MOVE MEM(FP@)@ FP
    SUB SP@ 1 SP
    JMP MEM(SP@)@
    ADD SP@ -5 SP
    MOVE FP@ SP
    MOVE MEM(FP@)@ FP
    SUB SP@ 1 SP
    JMP MEM(SP@)@
LAB END
