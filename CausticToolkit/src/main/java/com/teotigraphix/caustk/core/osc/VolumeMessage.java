
package com.teotigraphix.caustk.core.osc;

import com.teotigraphix.caustk.tone.components.VolumeEnvelopeComponent;

public class VolumeMessage extends CausticMessage {

    //--------------------------------------------------------------------------
    // Volume
    //--------------------------------------------------------------------------

    /**
     * Message: <code>/caustic/[machine_index]/volume_out [value]</code>
     * <p>
     * <strong>Default</strong>: <code>1.0</code> {@link ISubSynth},
     * <code>2.0</code> {@link IPCMSynth}.
     * <p>
     * <strong>Parameters</strong>:
     * <ul>
     * <li><strong>machine_index</strong>: The machine index.</li>
     * <li><strong>value</strong>: (0..2); (0..8 {@link IPCMSynth}) The volume
     * out value.</li>
     * </ul>
     * <p>
     * <strong>Returns</strong>: <code>float</code>
     * 
     * @see VolumeEnvelopeComponent#getOut()
     * @see VolumeEnvelopeComponent#setOut(float)
     */
    public static final VolumeMessage VOLUME_OUT = new VolumeMessage(
            "/caustic/${0}/volume_out ${1}");

    /**
     * Message: <code>/caustic/[machine_index]/volume_attack [value]</code>
     * <p>
     * <strong>Default</strong>: <code>0.0</code>
     * <p>
     * <strong>Parameters</strong>:
     * <ul>
     * <li><strong>machine_index</strong>: The machine index.</li>
     * <li><strong>value</strong>: (0..3.0625)</li>
     * </ul>
     * <p>
     * <strong>Returns</strong>: <code>float</code>
     * 
     * @see VolumeEnvelopeComponent#getAttack()
     * @see VolumeEnvelopeComponent#setAttack(float)
     */
    public static final VolumeMessage VOLUME_ATTACK = new VolumeMessage(
            "/caustic/${0}/volume_attack ${1}");

    /**
     * Message: <code>/caustic/[machine_index]/volume_decay [value]</code>
     * <p>
     * <strong>Default</strong>: <code>0.0</code>
     * <p>
     * <strong>Parameters</strong>:
     * <ul>
     * <li><strong>machine_index</strong>: The machine index.</li>
     * <li><strong>value</strong>: (0..3.0625)</li>
     * </ul>
     * <p>
     * <strong>Returns</strong>: <code>float</code>
     * 
     * @see VolumeEnvelopeComponent#getDecay()
     * @see VolumeEnvelopeComponent#setDecay(float)
     */
    public static final VolumeMessage VOLUME_DECAY = new VolumeMessage(
            "/caustic/${0}/volume_decay ${1}");

    /**
     * Message: <code>/caustic/[machine_index]/volume_sustain [value]</code>
     * <p>
     * <strong>Default</strong>: <code>1.0</code>
     * <p>
     * <strong>Parameters</strong>:
     * <ul>
     * <li><strong>machine_index</strong>: The machine index.</li>
     * <li><strong>value</strong>: (0..1.0)</li>
     * </ul>
     * <p>
     * <strong>Returns</strong>: <code>float</code>
     * 
     * @see VolumeEnvelopeComponent#getSustain()
     * @see VolumeEnvelopeComponent#setSustain(float)
     */
    public static final VolumeMessage VOLUME_SUSTAIN = new VolumeMessage(
            "/caustic/${0}/volume_sustain ${1}");

    /**
     * Message: <code>/caustic/[machine_index]/volume_release [value]</code>
     * <p>
     * <strong>Default</strong>: <code>0.0</code>
     * <p>
     * <strong>Parameters</strong>:
     * <ul>
     * <li><strong>machine_index</strong>: The machine index.</li>
     * <li><strong>value</strong>: (0..3.0625)</li>
     * </ul>
     * <p>
     * <strong>Returns</strong>: <code>float</code>
     * 
     * @see VolumeEnvelopeComponent#getRelease()
     * @see VolumeEnvelopeComponent#setRelease(float)
     */
    public static final VolumeMessage VOLUME_RELEASE = new VolumeMessage(
            "/caustic/${0}/volume_release ${1}");

    public VolumeMessage(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

}
