package no.ks.fiks.svarinn.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import no.ks.fiks.svarinn2.commons.MottattMeldingMetadata;

import java.time.Duration;
import java.util.List;

@Data
@Builder
public class MottattMelding implements Melding {
    @NonNull private MeldingId meldingId;
    @NonNull private String meldingType;
    @NonNull private KontoId avsenderKontoId;
    @NonNull private KontoId mottakerKontoId;
    @NonNull private Duration ttl;
    private MeldingId svarPaMelding;
    List<MottattPayload> dekryptertPayload;

    public static MottattMelding fromMottattMeldingMetadata(MottattMeldingMetadata melding, List<MottattPayload> mottattPayloads) {
        return MottattMelding.builder()
                .meldingId(new MeldingId(melding.getMeldingId()))
                .meldingType(melding.getMeldingType())
                .avsenderKontoId(new KontoId(melding.getAvsenderKontoId()))
                .mottakerKontoId(new KontoId(melding.getMottakerKontoId()))
                .ttl(Duration.ofMillis(melding.getTtl()))
                .svarPaMelding(melding.getSvarPaMelding() != null ? new MeldingId(melding.getSvarPaMelding()) : null)
                .dekryptertPayload(mottattPayloads)
                .build();
    }
}