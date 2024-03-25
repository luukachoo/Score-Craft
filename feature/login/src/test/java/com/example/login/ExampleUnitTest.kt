package com.example.login

import com.core.common.resource.Resource
import com.core.domain.use_case.auth.GetAuthUseCase
import com.core.domain.use_case.validation.ValidationUseCase
import com.example.login.event.LoginEvent
import com.example.login.screen.LoginFragmentViewModel
import com.google.firebase.auth.FirebaseUser
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class LoginFragmentViewModelTest {

    @Mock
    private lateinit var getAuthUseCase: GetAuthUseCase

    @Mock
    private lateinit var validationUseCase: ValidationUseCase

    @Mock
    private lateinit var mockFirebaseUser: FirebaseUser

    private lateinit var viewModel: LoginFragmentViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = LoginFragmentViewModel(getAuthUseCase, validationUseCase)
    }

    @Test
    fun `onEvent LogIn should update state and emit NavigateToHome when login is successful`() = runTest {
        val email = "test@example.com"
        val password = "password"
        `when`(getAuthUseCase.getLoginUseCase(email, password)).thenReturn(flowOf(Resource.Success(mockFirebaseUser)))
        `when`(validationUseCase.fieldValidatorUseCase(listOf(password))).thenReturn(true)

        viewModel.onEvent(LoginEvent.LogIn(email, password))

        verify(getAuthUseCase).getLoginUseCase(email, password)
        assertEquals(null, viewModel.logInState.value.errorMessage)
    }

    @Test
    fun `onEvent LogIn should update state with error message when login is unsuccessful`() = runTest {
        val email = "test@example.com"
        val password = "password"
        val errorMessage = "Login failed"
        `when`(getAuthUseCase.getLoginUseCase(email, password)).thenReturn(flowOf(Resource.Error(errorMessage)))
        `when`(validationUseCase.fieldValidatorUseCase(listOf(password))).thenReturn(true)

        viewModel.onEvent(LoginEvent.LogIn(email, password))

        verify(getAuthUseCase).getLoginUseCase(email, password)
        assertEquals(errorMessage, viewModel.logInState.value.errorMessage)
    }

    @Test
    fun `onEvent LogIn should not proceed when fields are not valid`() = runTest {
        val email = "test@example.com"
        val password = "password"
        `when`(validationUseCase.fieldValidatorUseCase(listOf(password))).thenReturn(false)

        viewModel.onEvent(LoginEvent.LogIn(email, password))

        verify(validationUseCase).fieldValidatorUseCase(listOf(password))
        assertEquals("Fields are not valid!", viewModel.logInState.value.errorMessage)
    }
}
