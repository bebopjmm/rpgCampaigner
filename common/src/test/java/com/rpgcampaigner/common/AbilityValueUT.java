package com.rpgcampaigner.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.rpgcampaigner.common.d20.AbilityEnum;
import com.rpgcampaigner.common.d20.AbilityValue;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author bebopjmm
 * @since 1/4/17
 */
@RunWith(MockitoJUnitRunner.class)

public class AbilityValueUT {

	@Test
	public void notifyOfChangeCascadesToAbilityModifierSubscriber() {
		ModifierListener mockedListener = mock(ModifierListener.class);
		DynamicValueListener mockedDVL = mock(DynamicValueListener.class);
		AbilityValue abilityValue = new AbilityValue(AbilityValue.DEFAULT_VALUE, AbilityEnum.STR);
		abilityValue.getAbilityModifier().getSubscribers().add(mockedListener);

		int orig = abilityValue.getAbilityModifier().getValue();
		abilityValue.setBaseValue(AbilityValue.DEFAULT_VALUE + 5);
		int revised = abilityValue.getAbilityModifier().getValue();
		verify(mockedListener).onModifierChange(revised - orig);

		abilityValue.getAbilityModifier().getSubscribers().remove(mockedListener);
		abilityValue.setBaseValue(AbilityValue.DEFAULT_VALUE);
		verify(mockedListener, atMost(1)).onModifierChange(anyInt());
	}
}
